package h06.problems;

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
        if (index == arr.length) {
            return -1;
        } else if (arr[index] == target) {
            return index;
        } else {
            return linearSearchRecursiveHelper(arr, target, index + 1);
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
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
