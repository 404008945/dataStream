import java.util.Objects;

public interface JoinPredicate<T1, T2> {

    boolean test(T1 o1, T2 o2);

    default JoinPredicate<T1, T2> and(JoinPredicate<? super T1, ? super T2> other) {
        Objects.requireNonNull(other);
        return (o1, o2) -> test(o1, o2) && other.test(o1, o2);
    }


    default JoinPredicate<T1, T2> or(JoinPredicate<? super T1, ? super T2> other) {
        Objects.requireNonNull(other);
        return (o1, o2) -> test(o1, o2) || other.test(o1, o2);
    }
}
