/**
 *
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the ArrayList class
 * 
 * @author sggephar
 * @author scheerl
 * @author mbabb
 */
public class ArrayListTest
{

    /**
     * Test method for ArrayList
     */
    @Test public final void testArrayList()
    {
        var arr = new ArrayList<Integer>() {
            {
                add(0, 1);
                add(0, 2);
            }
        };
        assertEquals(arr.size(), 2);
    }

    /**
     * Test method for Add
     */
    @Test public final void testAddIntE()
    {
        var arr = new ArrayList<Integer>() {
            {
                add(0, 990);
                add(0, 980);
            }
        };

        assertTrue(arr.get(0) == 980);

        for (int i = 0; i < 100; i++) {
            arr.add(0, i);
        }
        assertEquals(arr.size(), 102);

        try {
            arr.add(0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(arr.size(), 102);
        }

        try {
            arr.add(null);
            fail();
        } catch (NullPointerException e) {
            assertEquals(arr.size(), 102);
        }

        try {
            arr.set(-1, -1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(arr.size(), 102);
        }
    }

    /**
     * Test method for removing an element at a specific index
     * (remove method test)
     */
    @Test public final void testRemoveInt()
    {
        var arr = new ArrayList<Integer>() {
            {
                add(0, 1);
                add(0, 2);
            }
        };

        arr.remove(0);

        assertTrue(arr.get(0) == 1);
        assertEquals(arr.size(), 1);
    }

    /**
     * Test the set method
     * (to set an element at a specific index)
     */
    @Test public final void testSetIntE()
    {
        var arr = new ArrayList<Integer>() {
            {
                add(0, 1);
                add(0, 2);
            }
        };

        arr.set(0, 55);

        assertTrue(arr.get(0) == 55);
        assertEquals(arr.size(), 2);
    }

    /**
     * Test the get method
     * (to get element at a specific index)
     */
    @Test public final void testGetInt()
    {
        var arr = new ArrayList<Integer>() {
            {
                add(0, 1);
                add(0, 2);
            }
        };

        try {
            arr.get(9);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(arr.size(), 2);
        }
    }
}
