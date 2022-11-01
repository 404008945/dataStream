# 为什么使用java代码写报表
对于报表数据大部分情况下使用写sql的方式为大屏/报表提供数据来源，但是对于某些复杂情况下仅仅使用sql无法实现，或者实现起来困难的时候，会采取通过代码实现复杂的逻辑最终将结果返回。
# 遇到的问题
对于相对复杂的报表，经常需要做数据的连接即表与表的join，分组，计算等操作。sql天然支持这些操作，实现起来很轻松。但是当我们在java代码中需要对数据进行连接时，原生支持的就并不那么友好，我们常常会这么实现

---

现在有两个集合
```java
List<ContractDetail> contractDetails; // 合同明细集合，合同会重复
List<ContractInfo> contractInfos; // 合同主要信息，不会有重复合同
```
对应数据结构
```java

public class ContractDetail {
      /**
     * 合同编号
     */
    private String contractNo;

        /**
     * 总金额
     */
    private BigDecimal moneyTotal;
}

public class ContractInfo {
      /**
     * 合同编号
     */
    private String contractNo;

        /**
     * 状态
     */
    private String status;
}





```
需求
contractDetails 根据 contractNo关联 contractInfos，过滤出status = '已签订'的数据
再根据 contractDetails  中的contractNo分组，分别求每个 contractNo对应的moneyTotal之和
最终输出的应该为一个map
```java
  Map<String /* 合同编码 */, BigDecimal /* 对应moneyTotal之和 */> result;
```
通常我们会这么实现
```java
//  setp 1 过滤出 已签订状态的合同编码
Set<String> stopContract = contractInfos.stream()
                .filter(it -> "已签订".equals(it.getStatus()))
                .map(ContractInfo::getContractNo).collect(Collectors.toSet());


//step2 根据 step1的合同编码集合过滤出状态正确的contractDetail
  contractDetails = contractDetails.stream()
                .filter(it -> stopContract.contains(it.getContractNo()))
                .collect(Collectors.toList());

//step3 根据contractNo分别累加对应的moneyTotal
 Map<String, BigDecimal> result = new HashMap<>();
 contractDetails.stream().forEach(it -> {
            BigDecimal moneyTotal = Optional.ofNullable(result.get(it.getContractNo()))
                    .orElse(BigDecimal.ZERO);
            moneyTotal = moneyTotal.add(it.getMoneyTotal() != null ? it.getMoneyTotal() : BigDecimal.ZERO);
            result.put(it.getContractNo(), moneyTotal);
        });
```
**显然这个实现时比较复杂的，因为使用sql的话无非就是 join 连接之后加上group by分组。求和。就可以轻易解决这个问题。那么看看后面这个工具类，再思考有没有更简单的办法实现。**
# 工具类
## CollectionDataStream

集合数据流CollectionDataStream的功能是通过接口对集合之间做关联，实现了类似sql join和left join两个操作
并且实现和java中的Stream相互转换的功能。
聚合数据结构将集合转换成类似表结构的数据结构，包含表名，数据
```java
public class AggregationData {
    Map<String, Map> aggregationMap;

    private AggregationData(){
        aggregationMap = new HashMap<>();
    }

    //key 为别名，value为对应对象
    public AggregationData(String tableName, Object data) {
        aggregationMap = new HashMap<>();
        aggregationMap.put(tableName, BeanUtil.beanToMap(data));
    }

    public Map<String, Map> getRowAllData() {
        return aggregationMap;
    }

    public Map getTableData(String tableName) {
        if (!aggregationMap.containsKey(tableName)) {
            throw new DataStreamException(tableName + ".not.exists");
        }
        return aggregationMap.get(tableName);
    }

    public void setTableData(String tableName, Object data) {
        if(aggregationMap.containsKey(tableName)){
            throw new DataStreamException(tableName+".has.been.exists!");
        }
        aggregationMap.put(tableName, BeanUtil.beanToMap(data));
    }


    private void setTableData(String tableName, Map<String, Object> data) {
        Map<String, Object> tableData =
                Optional.ofNullable(aggregationMap.get(tableName)).orElse(new HashMap<String, Object>());
        tableData.putAll(data);
        aggregationMap.put(tableName, tableData);
    }

    public AggregationData copyAggregationData() {
        AggregationData aggregationData = new AggregationData();
        for (String tableName : this.getRowAllData().keySet()) {
            aggregationData.setTableData(tableName, this.getRowAllData().get(tableName));
        }
        return aggregationData;
    }
}
```
AggregationData代表一行数据，aggregationMap的key为表名，value为对应的数据
来详细看看这个接口
```java
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public interface CollectionDataStream<T> {

    /**
     *将集合转化为数据流，并给一个别名
     * @param tableName
     * @param collection
     * @return
     */
    static CollectionDataStream<AggregationData> of(String tableName, Collection<?> collection) {
        return new CollectionDataStreamImpl(tableName, collection);
    }
    /**
     *将 Stream转化为数据流，并给一个别名
     * @param tableName
     * @param collection
     * @return
     */
    static CollectionDataStream<AggregationData> of(String tableName, Stream<?> collection) {
        return new CollectionDataStreamImpl(tableName, collection);
    }

    /**
     * 内连接，可自定义连接条件，使用双循环
     *
     * @param tableName
     * @param collection
     * @param predict
     * @param <T1>
     * @return
     */
    <T1> CollectionDataStream<T> join(String tableName, Collection<T1> collection, JoinPredicate<T, T1> predict);

    /**
     * 等值内连接，使用map优化
     *
     * @param collection
     * @param tableName
     * @param aggregationMapper
     * @param dataValueMapper
     * @param <T1>
     * @param <R>
     * @return
     */
    //等值条件推荐用法
    <T1, R> CollectionDataStream<T> joinUseHashOnEqualCondition(String tableName, Collection<T1> collection, Function<T, R> aggregationMapper, Function<T1, R> dataValueMapper);


    /**
     * 左连接，可自定义连接条件，使用双循环
     *
     * @param tableName
     * @param collection
     * @param predict
     * @param <T1>
     * @return
     */
    <T1> CollectionDataStream<T> leftJoin(String tableName, Collection<T1> collection, JoinPredicate<T, T1> predict);

    /**
     * 等值左连接，使用map优化
     *
     * @param collection
     * @param tableName
     * @param aggregationMapper
     * @param dataValueMapper
     * @param <T1>
     * @param <R>
     * @return
     */
    <T1, R> CollectionDataStream<T> leftJoinUseHashOnEqualCondition( String tableName, Collection<T1> collection,Function<T, R> aggregationMapper, Function<T1, R> dataValueMapper);

    Stream<T> toStream();

    Stream<Map> toStream(String tableName);

    <R> Stream<R> toStream(String tableName, Class<R> clzz);


    <R> Stream<R> toStream(Function<AggregationData, R> mapper);
}

```
注意joinUseHashOnEqualCondition和join两个方法的区别。
**如果集合之间的连接时某个字段等值连接，那么使用joinUseHashOnEqualCondition，其内部使用的是map分组之后进行连接。而直接使用join的话连接条件可自定义，但是是通过双重循环进行条件判断，效率较低。因此等值情况下，使用joinUseHashOnEqualCondition效率更高。**

### 如何使用
还是已上面的需求为例
先进行两个集合之间的连接
```java
 CollectionDataStream.of("t1", contractDetails) .joinUseHashOnEqualCondition(
                        contractInfos.stream().filter(it -> "已签订".equals(it.getStatus())).collect(Collectors.toList()),
                        "t2",
                        agg -> agg.getTableData("t1").get("contractNo"),
                        ContractInfo::getContractNo
                );

```
代码解析
```java
 CollectionDataStream.of("t1", contractDetails)
```
是将集合contractDetails转换为表名为t1的数据流，
```java
 .joinUseHashOnEqualCondition(
                        contractInfos.stream().filter(
                          "t2",
                            it -> "已签订".equals(it.getStatus())).collect(Collectors.toList()),
                        agg -> agg.getTableData("t1").get("contractNo"),
                        ContractInfo::getContractNo
                );
```
内连接contractInfos，同时给contractInfos起别名t2，连接条件是等值连接 t1的contractNo和contractInfos的contractNol连接之后得到新的聚合数据流
当然也可以使用自定义的连接实现
```java
CollectionDataStream.of("t1", contractDetails)
                .join("t2",
                        contractInfos.stream().filter(it -> "已签订".equals(it.getStatus())).collect(Collectors.toList()),
                        (agg, data) -> agg.getTableData("t1").get("contractNo").equals(data.getContractNo())
                )
```
**这里通过joinUseHashOnEqualCondition内连接，那么也起到了一个过滤的作用。连接完成之后我们还要分组进行计算，那么就需要用到下一个工具类**
## MyCollectors
是对stram中原生Collectors的一个扩展，实现了更多做报表常用分组的一些操作，
```java
MyCollectorspackage collector;

import utils.NumberUtil;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MyCollectors {
    /**
     * 返回一个Collector用于对集合进行分组并且，对于组内有多个元素，只返回最后一个，其他的忽略
     * 适用于明确分组key唯一的情况，value可为空
     * 谨慎使用，如果分组有多条，会丢失数据！！！
     * @param keyMapper
     * @param <T>
     * @param <K>
     * @param <U>
     * @param <M>
     * @return
     */
    public static <T, K, U, M extends Map<K, U>>
    Collector<T, ?, Map<K, U>> groupingByLast(Function<? super T, ? extends K> keyMapper,
                                               Function<? super T, ? extends U> valueMapper) {
        return Collectors.groupingBy(keyMapper, Collectors.reducing(null, valueMapper, (o1, o2) -> o2));
    }


    /**
     * 传入一个keyMaper和一个比较器
     * 根据key分组，组内使用比较器进行比较，最终得到一个最大结果
     * @param keyMapper
     * @param comparator
     * @param <T>
     * @param <K>
     * @param <U>
     * @param <M>
     * @return
     */
    public static <T, K, U, M extends Map<K, U>>
    Collector<T, ?, Map<K, T>> groupingByMaxComparator(Function<? super T, ? extends K> keyMapper,
                                                      Comparator<T> comparator) {
        return Collectors.groupingBy(keyMapper, Collectors.collectingAndThen(Collectors.maxBy(comparator), it -> it.orElse(null)));
    }

    /**
     * 传入一个keyMaper和一个比较器
     * 根据key分组，组内使用比较器进行比较，最终得到一个最小结果
     * @param keyMapper
     * @param comparator
     * @param <T>
     * @param <K>
     * @param <U>
     * @param <M>
     * @return
     */
    public static <T, K, U, M extends Map<K, U>>
    Collector<T, ?, Map<K, T>> groupingByMinComparator(Function<? super T, ? extends K> keyMapper,
                                                       Comparator<T> comparator) {
        return Collectors.groupingBy(keyMapper, Collectors.collectingAndThen(Collectors.maxBy(comparator), it -> it.orElse(null)));
    }


    /**
     * 分组后组内按照指定字段求和
     * @param keyMapper
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K>
    Collector<T, ?, Map<K, BigDecimal>> groupingAndSum(Function<? super T, ? extends K> keyMapper,
                                                       Function<? super T, BigDecimal> valueMapper) {
        return Collectors.groupingBy(keyMapper, Collectors.reducing(BigDecimal.ZERO, valueMapper, NumberUtil::addNumbers));
    }



    /**
     * 根据对象某个字段进行求和
     * @param mapper
     * @param <T>
     * @return
     */
    public static <T>
    Collector<T, ?, BigDecimal> sumByField(Function<? super T, ? extends BigDecimal> mapper) {
        return Collectors.reducing(BigDecimal.ZERO, mapper, NumberUtil::addNumbers);
    }

    /**
     * 求和
     */
    public static Collector<BigDecimal, ?, BigDecimal> sum() {
        return Collectors.reducing(BigDecimal.ZERO, NumberUtil::addNumbers);
    }
}



```
## 组合使用的实现
```java
 Map<String /* 合同变么 */, BigDecimal /* 对应moneyTotal之和 */> result = CollectionDataStream.of("t1", contractDetails)
                .joinUseHashOnEqualCondition(
                        contractInfos.stream().filter(it -> "60".equals(it.getStatus())).collect(Collectors.toList()),
                        "t2",
                        agg -> agg.getTableData("t1").get("contractNo"),
                        ContractInfo::getContractNo
                ).toStream("s1", ContractDetail.class)//将数据流转换为 java原生Stream
                .collect(MyCollectors.groupingAndSum(ContractDetail::getContractNo, ContractDetail::getMoneyTotal));

```
这样的实现显然更加简单，也减少了出错的的概率，减少了代码量，提升了效率。

## 优势

1. 实现了集合之间的连接操作，并且是流式操作，可以一口气不断连接多个集合。
2. 实现了与Stream之间的相互转换。利用stream的功能可以实现各种复杂操作，例如过滤，转换，分组等。
3. 效率上有一定的保证，对于等值连接采用了Map优化，并且在内连接时，考虑使用后小表连大表进行优化，在一些情况下减少循环次数，在bean转换为行聚合数据时使用cglib下的BeanMap减少内存的占用和性能的消耗
