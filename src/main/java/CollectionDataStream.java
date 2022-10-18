import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public interface CollectionDataStream<T> {

    static CollectionDataStream<AggregationData> of(String tableName, Collection<?> collection) {
        return new CollectionDataStreamImpl(tableName, collection);
    }

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
    <T1, R> CollectionDataStream<T> joinUseHashOnEqualCondition(Collection<T1> collection, String tableName, Function<T, R> aggregationMapper, Function<T1, R> dataValueMapper);


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
    <T1, R> CollectionDataStream<T> leftJoinUseHashOnEqualCondition(Collection<T1> collection, String tableName, Function<T, R> aggregationMapper, Function<T1, R> dataValueMapper);

    Stream<T> toStream();

    Stream<Map> toStream(String tableName);

    <R> Stream<R> toStream(String tableName, Class<R> clzz);


    <R> Stream<R> toStream(Function<AggregationData, R> mapper);
}
