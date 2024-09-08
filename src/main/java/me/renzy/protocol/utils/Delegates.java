package me.renzy.protocol.utils;

import lombok.NonNull;
import me.renzy.protocol.interfaces.Delegate;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class Delegates {

    @NonNull
    public static <T,U> ConsumerToBiConsumer<T,U> consumerToBiConsumer(@NonNull Consumer<U> consumer) {
        return new ConsumerToBiConsumer<>(consumer);
    }

    @NonNull
    public static <T,U> PredicateToBiPredicate<T,U> predicateToBiPredicate(@NonNull Predicate<U> predicate) {
        return new PredicateToBiPredicate<>(predicate);
    }

    private static abstract class AbstractDelegate<T> implements Delegate<T> {

        final T delegate;


        private AbstractDelegate(T delegate) {
            this.delegate = delegate;
        }

        @Override
        @NonNull
        public T getDelegate() {
            return this.delegate;
        }
    }

    private static final class ConsumerToBiConsumer<T,U> extends AbstractDelegate<Consumer<U>> implements BiConsumer<T,U> {

        private ConsumerToBiConsumer(Consumer<U> delegate) {
            super(delegate);
        }

        @Override
        public void accept(T t, U u) {
            this.delegate.accept(u);
        }
    }

    private static final class PredicateToBiPredicate<T,U> extends AbstractDelegate<Predicate<U>> implements BiPredicate<T,U> {

        private PredicateToBiPredicate(Predicate<U> delegate) {
            super(delegate);
        }

        @Override
        public boolean test(T t, U u) {
            return this.delegate.test(u);
        }

    }

    private Delegates(){}
}
