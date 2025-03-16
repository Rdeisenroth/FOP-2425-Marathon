package h10.rubric;

/**
 * A builder interface for building objects.
 *
 * @param <T> the type of object to build
 */
public interface Builder<T> {

    /**
     * Builds the object.
     *
     * @return the built object
     */
    T build();
}
