package h08.util;

import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a function that accepts three arguments and produces a result.
 *
 * @param <A> the type of the first argument to the function
 * @param <B> the type of the second argument to the function
 * @param <C> the type of the third argument to the function
 * @param <R> the type of the result of the function
 *
 * @author Nhan Huynh
 */
public interface TriFunction<A, B, C, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param a the first function argument
     * @param b the second function argument
     * @param c the third function argument
     *
     * @return the result of the function
     */
    R apply(A a, B b, C c);

    /**
     * Returns a composed function that first applies this function to its input.
     *
     * @param after and then applies the {@code after} function to the result.
     * @param <V>   the type of output of the {@code after} function
     *
     * @return a composed function that first applies this function and then applies the {@code after} function
     */
    default <V> TriFunction<A, B, C, V> andThen(
        Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (A a, B b, C c) -> after.apply(apply(a, b, c));
    }
}
