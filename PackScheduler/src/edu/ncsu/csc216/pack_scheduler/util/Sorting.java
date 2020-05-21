package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Utility class for implementing, quicksort. For more information regarding
 * quicksort, visit: https://en.wikipedia.org/wiki/Quicksort
 *
 * @author Michael Babb
 * @author Amanda Richardson
 * @author Preston Dudley
 */
public class Sorting
{

    /**
     * Swaps two values within arr, located at positions ix1 and ix2.
     *
     * @param <T> a generic comparable type.
     * @param arr input array.
     * @param ix1 first swap position.
     * @param ix2 second swap position.
     */
    public static <T> void swap(T[] arr, int ix1, int ix2)
    {
        assert ix1 < arr.length && ix2 < arr.length;
        var t = arr[ix1];
        arr[ix1] = arr[ix2];
        arr[ix2] = t;
    }

    /**
     * Implements the Lomuto partition scheme for partitioning upon a pivot.
     *
     * @param <T> a generic comparable type.
     * @param arr input array.
     * @param lo starting value of the sub array.
     * @param hi ending value of the sub array.
     * @return the final pivot index.
     */
    public static <T extends Comparable<T>> int
    partition(T[] arr, int lo, int hi)
    {
        var pivot = arr[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (arr[j].compareTo(pivot) < 0) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, hi);
        return i;
    }

    /**
     * Recursive driver function for the quicksort algorithm. See the class
     * comment above for more information.
     *
     * @param <T> a generic comparable type.
     * @param arr input array.
     * @param lo starting value of the sub array.
     * @param hi ending value of the sub array.
     */
    public static <T extends Comparable<T>> void
    quickSortImpl(T[] arr, int lo, int hi)
    {
        if (lo < hi) {
            var pivot = partition(arr, lo, hi);
            quickSortImpl(arr, lo, pivot - 1);
            quickSortImpl(arr, pivot + 1, hi);
        }
    }

    /**
     * Implements the quicksort algorithm for a generic comparable array of type
     * T.
     * @param <T> a generic comparable type.
     * @param arr input array.
     */
    public static <T extends Comparable<T>> void quickSort(T[] arr)
    {
        int n = arr.length - 1;
        quickSortImpl(arr, 0, n);
    }
}