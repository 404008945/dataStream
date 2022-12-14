
import exception.DataStreamException;
import org.apache.commons.collections.CollectionUtils;
import utils.BeanUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionDataStreamImpl implements CollectionDataStream<AggregationData> {

    private List<AggregationData> aggregationDatas;

    final private Map emptyMap =  Collections.unmodifiableMap(new HashMap<>());

    public CollectionDataStreamImpl(String tableName, Collection<?> collection) {
        if (collection == null) {
            throw new DataStreamException("collection.can.not.be.null");
        }
        this.aggregationDatas = collection.stream()
                .map(it -> new AggregationData(tableName, it)).collect(Collectors.toList());
    }

    public CollectionDataStreamImpl( Collection<AggregationData> collection) {
        if (collection == null) {
            throw new DataStreamException("collection.can.not.be.null");
        }
        this.aggregationDatas = collection.stream().collect(Collectors.toList());
    }

    public CollectionDataStreamImpl(String tableName, Stream<?> stream) {
        if (stream == null) {
            throw new DataStreamException("stream.can.not.be.null");
        }
        this.aggregationDatas = stream.map(it -> new AggregationData(tableName, it)).collect(Collectors.toList());
    }
    public CollectionDataStreamImpl(Stream<AggregationData> stream) {
        if (stream == null) {
            throw new DataStreamException("stream.can.not.be.null");
        }
        this.aggregationDatas = stream.collect(Collectors.toList());
    }


    @Override
    public <T1> CollectionDataStream<AggregationData> join( String tableName,Collection<T1> collection, JoinPredicate<AggregationData, T1> predict) {
        List<AggregationData> newAggregationDatas = new ArrayList<>();
        aggregationDatas.forEach(aggregationData -> {
            for (T1 data : collection) {
                if (predict.test(aggregationData, data)) {
                    AggregationData copyData = aggregationData.copyAggregationData();
                    copyData.setTableData(tableName, data);
                    newAggregationDatas.add(copyData);
                }
            }
        });
        //??????????????????????????????
        this.aggregationDatas = newAggregationDatas;
        return this;
    }

    @Override
    public <T1, R> CollectionDataStream<AggregationData> joinUseHashOnEqualCondition(
            String tableName,Collection<T1> collection,  Function<AggregationData, R> aggregationMapper, Function<T1, R> dataValueMapper) {
        if (this.aggregationDatas.size() <= collection.size()) {
            return joinUseHashOnEqualConditionLeftMain(collection,tableName,aggregationMapper,dataValueMapper);
        } else {
            return joinUseHashOnEqualConditionRightMain(collection,tableName,aggregationMapper,dataValueMapper);
        }
    }

    private  <T1, R> CollectionDataStream<AggregationData> joinUseHashOnEqualConditionLeftMain(
            Collection<T1> collection, String tableName, Function<AggregationData, R> aggregationMapper, Function<T1, R> dataValueMapper) {
        Map<R, List<T1>> collectionMap = collection.stream().collect(Collectors.groupingBy(dataValueMapper));
        List<AggregationData> newAggregationDatas = new ArrayList<>();
        aggregationDatas.forEach(aggregationData -> {
            R joinKey = aggregationMapper.apply(aggregationData);
            if(collectionMap.containsKey(joinKey)) {
                for (T1 data : collectionMap.get(joinKey)) {
                    AggregationData copyData = aggregationData.copyAggregationData();
                    copyData.setTableData(tableName, data);
                    newAggregationDatas.add(copyData);
                }
            }
        });
        //??????????????????????????????
        this.aggregationDatas = newAggregationDatas;
        return this;
    }

    private <T1, R> CollectionDataStream<AggregationData> joinUseHashOnEqualConditionRightMain(
            Collection<T1> collection, String tableName, Function<AggregationData, R> aggregationMapper, Function<T1, R> dataValueMapper) {
        Map<R, List<AggregationData>> aggregationMap = this.aggregationDatas.stream().collect(Collectors.groupingBy(aggregationMapper));
        List<AggregationData> newAggregationDatas = new ArrayList<>();
        collection.forEach(data -> {
            R joinKey = dataValueMapper.apply(data);
            if (aggregationMap.containsKey(joinKey)) {
                for (AggregationData aggregationData : aggregationMap.get(joinKey)) {
                    AggregationData copyData = aggregationData.copyAggregationData();
                    copyData.setTableData(tableName, data);
                    newAggregationDatas.add(copyData);
                }
            }
        });
        //??????????????????????????????
        this.aggregationDatas = newAggregationDatas;
        return this;
    }


    @Override
    public <T1> CollectionDataStream<AggregationData> leftJoin( String tableName,Collection<T1> collection, JoinPredicate<AggregationData, T1> predict) {
        if (CollectionUtils.isEmpty(collection)) {
            for (AggregationData aggregationData : this.aggregationDatas) {
                aggregationData.setTableData(tableName, emptyMap);
            }
            return this;
        }
        List<AggregationData> newAggregationDatas = new ArrayList<>();
        aggregationDatas.forEach(aggregationData -> {
            boolean joinFlag = false;
            for (T1 data : collection) {
                if (predict.test(aggregationData, data)) {
                    AggregationData copyData = aggregationData.copyAggregationData();
                    copyData.setTableData(tableName, data);
                    newAggregationDatas.add(copyData);
                    joinFlag = true;
                }
            }
            if (!joinFlag) {//???????????????????????????
                AggregationData data = aggregationData.copyAggregationData();
                data.setTableData(tableName,emptyMap);
                newAggregationDatas.add(data);
            }
        });
        //??????????????????????????????
        this.aggregationDatas = newAggregationDatas;
        return this;
    }

    @Override
    public <T1, R> CollectionDataStream<AggregationData> leftJoinUseHashOnEqualCondition(
            String tableName,Collection<T1> collection,  Function<AggregationData, R> aggregationMapper, Function<T1, R> dataValueMapper) {
        if (CollectionUtils.isEmpty(collection)) {
            for (AggregationData aggregationData : this.aggregationDatas) {
                aggregationData.setTableData(tableName, emptyMap);
            }
            return this;
        }
        Map<R, List<T1>> collectionMap = collection.stream().collect(Collectors.groupingBy(dataValueMapper));
        List<AggregationData> newAggregationDatas = new ArrayList<>();
        aggregationDatas.forEach(aggregationData -> {
            boolean joinFlag = false;
            R joinKey = aggregationMapper.apply(aggregationData);
            if(collectionMap.containsKey(joinKey)) {
                joinFlag = true;
                for (T1 data : collectionMap.get(joinKey)) {
                    AggregationData copyData = aggregationData.copyAggregationData();
                    copyData.setTableData(tableName, data);
                    newAggregationDatas.add(copyData);
                }
            }
            if (!joinFlag) {//???????????????????????????
                AggregationData data = aggregationData.copyAggregationData();
                data.setTableData(tableName,emptyMap);
                newAggregationDatas.add(data);
            }
        });
        //??????????????????????????????
        this.aggregationDatas = newAggregationDatas;
        return this;
    }


    @Override
    public Stream<AggregationData> toStream() {
        return aggregationDatas.stream();
    }


    public Stream<Map> toStream(String tableName) {
        return aggregationDatas.stream().map(it -> it.getTableData(tableName));
    }

    public <R> Stream<R> toStream(String tableName, Class<R> clzz) {
        return aggregationDatas.stream().map(it -> BeanUtil.mapToBean(it.getTableData(tableName),clzz));
    }


    public <R> Stream<R> toStream(Function<AggregationData,R> mapper) {
        return aggregationDatas.stream().map(it -> mapper.apply(it));
    }







}
