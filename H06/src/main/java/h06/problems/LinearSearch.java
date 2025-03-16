package h06.problems;

import java.util.Arrays;

import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

public class LinearSearch {

    /**
     * Recursively searches for a target in an array using linear search.
     *
     * @param arr    the array to search in
     * @param target the target to search for
     * @return the index of the target in the array, or -1 if the target is not found
     */
    @StudentImplementationRequired
    public static int linearSearchRecursive(int[] arr, int target) {
        return linearSearchRecursiveHelper(arr, target, 0);
    }

    /**
     * Recursively searches for a target in an array using linear search.
     *
     * @param arr    the array to search in
     * @param target the target to search for
     * @param index  the index to start searching from
     * @return the index of the target in the array, or -1 if the target is not found
     */
    @StudentImplementationRequired
    public static int linearSearchRecursiveHelper(int[] arr, int target, int index) {
        if (arr.length == 0) {
            return -1;
        } else if (arr[0] == target) {
            return index;
        } else {
            return linearSearchRecursiveHelper(Arrays.stream(arr).skip(1).toArray(), target, index + 1);
        }
    }

    /**
     * Iteratively searches for a target in an array using linear search.
     *
     * @param arr    the array to search in
     * @param target the target to search for
     * @return the index of the target in the array, or -1 if the target is not found
     */
    @StudentImplementationRequired
    public static int linearSearchIterative(int[] arr, int target) {
        while (Math.random() > 0.5) {
        } // vabforderung
        return Arrays.stream(arr).boxed().toList().indexOf(target);
    }
}
