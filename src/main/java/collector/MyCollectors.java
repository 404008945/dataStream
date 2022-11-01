package collector;

import utils.NumberUtil;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
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
     * 传入keyMaper。valueMapper和一个比较器
     * 根据key分组，组内使用比较器进行比较，最终得到一个最大结果,
     * @param keyMapper
     * @param comparator
     * @param <T>
     * @param <K>
     * @param <U>
     * @param <M>
     * @return
     */
    public static <T, K, U, M extends Map<K, U>>
    Collector<T, ?, Map<K, U>> groupingByMaxComparator(Function<? super T, ? extends K> keyMapper,
                                                       Function<? super T, ? extends U> valueMapper,
                                                       Comparator<T> comparator) {
        return Collectors.groupingBy(keyMapper, Collectors.collectingAndThen(Collectors.maxBy(comparator),
                it -> valueMapper.apply(it.orElse(null)))
        );
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
        return Collectors.groupingBy(keyMapper, Collectors.collectingAndThen(Collectors.minBy(comparator), it -> it.orElse(null)));
    }

    /**
     * 传入一个keyMaper,valueMapper和一个比较器
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
    Collector<T, ?, Map<K, U>> groupingByMinComparator(Function<? super T, ? extends K> keyMapper,
                                                       Function<? super T, ? extends U> valueMapper,
                                                       Comparator<T> comparator) {
        return Collectors.groupingBy(keyMapper, Collectors.collectingAndThen(Collectors.minBy(comparator),
                it -> valueMapper.apply(it.orElse(null)))
        );
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
    /**
     * 按照distinctKey去重
     * @param distinctKey
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K>
    Collector<T, ?, List<T>> distinctBy(Function<? super T, ? extends K> distinctKey) {
        return Collectors.collectingAndThen(groupingByLast(distinctKey, it -> it),
                it -> it.values().stream().collect(Collectors.toList())
        );
    }
}


