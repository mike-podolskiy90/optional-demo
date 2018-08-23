package com.epam.mix.optionaldemo.upgrade;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Maybe<T> {

    private static final Maybe<?> EMPTY = new Maybe<>();

    private final T value;

    private Maybe() {
        this.value = null;
    }

    private Maybe(final @NotNull T value) {
        this.value = value;
    }

    @SuppressWarnings("unchecked")
    public static <T> @NotNull Maybe<T> empty() {
        return (Maybe<T>) EMPTY;
    }

    @SuppressWarnings("unchecked")
    public static <T> @NotNull Maybe<T> of(final @Nullable T value) {
        return value == null ? empty() : new Maybe(value);
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static <T> @NotNull Maybe<T> of(final @NotNull Optional<T> optValue) {
        return optValue.map(Maybe::of)
                .orElseGet(Maybe::empty);
    }

    public static <T> @NotNull Optional<T> toOptional(final @NotNull Maybe<T> maybeValue) {
        return maybeValue.map(Optional::of)
                .orElseGet(Optional::empty);
    }

    public Maybe<T> ifPresent(final @NotNull Consumer<? super T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }

        return this;
    }

    public Maybe<T> ifAbsent(final @NotNull Runnable emptyAction) {
        if (value == null) {
            emptyAction.run();
        }

        return this;
    }

    public void ifPresentOrElse(final @NotNull Consumer<? super T> action, final @NotNull Runnable emptyAction) {
        if (value != null) {
            action.accept(value);
        } else {
            emptyAction.run();
        }
    }

    public @NotNull Maybe<T> filter(final @NotNull Predicate<? super T> predicate) {
        if (value == null) {
            return this;
        } else {
            return predicate.test(value) ? this : empty();
        }
    }

    public <U> @NotNull Maybe<U> map(final @NotNull Function<? super T, ? extends U> mapper) {
        if (value == null) {
            return empty();
        } else {
            return Maybe.of(mapper.apply(value));
        }
    }

    public <U, V> @NotNull Maybe<V> chainMap(final @NotNull Function<? super T, ? extends U> firstMapper,
                                             final @NotNull Function<? super U, ? extends V> secondMapper) {
        return map(firstMapper).map(secondMapper::apply);
    }

    public <U, V, W> @NotNull Maybe<W> chainMap(final @NotNull Function<? super T, ? extends U> firstMapper,
                                                final @NotNull Function<? super U, ? extends V> secondMapper,
                                                final @NotNull Function<? super V, ? extends W> thirdMapper) {
        return chainMap(firstMapper, secondMapper).map(thirdMapper::apply);
    }

    public <U> @NotNull Maybe<U> flatMap(final @NotNull Function<? super T, Maybe<U>> mapper) {
        if (value == null) {
            return empty();
        } else {
            return mapper.apply(value);
        }
    }

    @SuppressWarnings("unchecked")
    public @NotNull Maybe<T> or(final @NotNull Supplier<? extends Maybe<? extends T>> supplier) {
        if (value != null) {
            return this;
        } else {
            return (Maybe<T>) supplier.get();
        }
    }

    public @NotNull T orElse(final @NotNull T other) {
        return value != null ? value : other;
    }

    public @Nullable T orNull() {
        return value;
    }

    public @NotNull T orElseGet(final @NotNull Supplier<? extends T> other) {
        return value != null ? value : other.get();
    }

    public <X extends Throwable> @NotNull T orElseThrow(final @NotNull Supplier<? extends X> exceptionSupplier) throws X {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    public @NotNull Stream<T> stream() {
        if (value == null) {
            return Stream.empty();
        } else {
            return Stream.of(value);
        }
    }

    @Override
    public boolean equals(final @Nullable Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Maybe<?> that = (Maybe<?>) o;

        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public @NotNull String toString() {
        return value != null
                ? String.format("Maybe[%s]", value)
                : "Maybe.empty";
    }
}

