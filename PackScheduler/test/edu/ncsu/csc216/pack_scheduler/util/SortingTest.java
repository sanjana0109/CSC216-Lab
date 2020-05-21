package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.util.Sorting;

/**
 * For testing the sorting class within utils.
 *
 * @author Michael Babb
 * @author Amanda Richardson
 * @author Preston Dudley
 */
public class SortingTest
{

    /** For testing the constructors hereof. */
    @Test public void testConstructorsHereof()
    {
        var c1 = new Sorting();
        assertNotNull(c1);
    }

    /** Tests a custom quicksort implementation. */
    @Test public void testSorting()
    {

        int a = 0;
        int b = 100;
        int n = 50;

        var uarr = new Random()
                       .ints(n, a, b)
                       .boxed()
                       .collect(Collectors.toList())
                       .toArray(Integer[] ::new);

        var arr1 = uarr.clone();

        var arr2 = Arrays.asList(uarr);

        Sorting.quickSort(arr1);
        arr2.sort((i, j) -> { return i.compareTo(j); });

        assertTrue(Arrays.deepEquals(arr1, arr2.toArray(Integer[] ::new)));
    }
}