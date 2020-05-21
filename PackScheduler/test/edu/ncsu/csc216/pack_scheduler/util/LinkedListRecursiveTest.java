/**
 *LinkedListRecursiveTest.java
 * @author Nicholas Loftin nlloftin@ncsu.edu
 * Version 1
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Recursive list test
 * @author Nicholas Loftin nlloftin@ncsu.edu
 *
 */
public class LinkedListRecursiveTest {

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#LinkedListRecursive()}.
     */
    @Test
    public void testLinkedListRecursive() throws Exception {
        LinkedListRecursive<String> list = new LinkedListRecursive<String>();
        assertTrue(list instanceof LinkedListRecursive);
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#isEmpty()}.
     */
    @Test
    public void testIsEmpty() throws Exception {
        LinkedListRecursive<String> list = new LinkedListRecursive<String>();
        assertTrue(list.isEmpty());
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#size()}.
     */
    @Test
    public void testSize() throws Exception {
        LinkedListRecursive<String> list = new LinkedListRecursive<String>();
        assertEquals(0, list.size());
        list.add("BEANS");
        assertEquals(1, list.size());
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#add(java.lang.Object)}.
     */
    @Test
    public void testAddE() throws Exception {
        LinkedListRecursive<String> list = new LinkedListRecursive<String>();
        assertTrue(list.add("FWAG"));
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#add(int, java.lang.Object)}.
     */
    @Test
    public void testAddIntE() throws Exception {
        LinkedListRecursive<String> list = new LinkedListRecursive<String>();
        list.add("BEANS");
        list.add("BEANS2");
        list.add("BEANS3");
        list.add("BEANS4");
//        for(int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
        assertEquals("BEANS", list.get(0));
        assertEquals("BEANS2", list.get(1));
        assertEquals("BEANS3", list.get(2));
        assertEquals("BEANS4", list.get(3));
        try {
            list.add(3, null);
        } catch (Exception e) {
            assertNull(e.getMessage());
        }
        try {
            list.add(3, "BEANS");
        } catch (Exception e) {
            assertNull(e.getMessage());
        }
        try {
            list.add(-1, "BEANS22222");
        } catch (Exception e) {
            assertNull(e.getMessage());
        }
        list.add(0, "BOYE");

    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#get(int)}.
     */
    @Test
    public void testGet() throws Exception {
        LinkedListRecursive<String> list = new LinkedListRecursive<String>();
        list.add(0, "BEANS");
        list.add(0, "BEANS1");
        list.add(2, "BEANS2");
        assertEquals("BEANS1", list.get(0));
        try {
            list.get(-1);
        } catch (Exception e) {
            assertNull(e.getMessage());
        }
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#remove(int)}.
     */
    @Test
    public void testRemoveInt() throws Exception {
        LinkedListRecursive<String> list = new LinkedListRecursive<String>();
//        try {
//            list.remove(0);
//        } catch (Exception e) {
//            assertEquals("List is empty", e.getMessage());
//        }
        list.add("BEANS");
        list.add("BEANS2");
        list.add("BEANS3");
        list.add("BEANS4");
        assertEquals("BEANS2", list.remove(1));
        assertEquals("BEANS3", list.get(1));
        try {
            list.remove(-1);
        } catch (Exception e) {
            assertNull(e.getMessage());
        }
        assertEquals("BEANS", list.remove(0));
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#remove(java.lang.Object)}.
     */
    @Test
    public void testRemoveE() throws Exception {
        LinkedListRecursive<String> list = new LinkedListRecursive<String>();
        list.add("BEANS");
        list.add("BEANS2");
        list.add("BEANS3");
        list.add("BEANS4");
        assertTrue(list.remove("BEANS3"));
        assertEquals("BEANS4", list.get(2));
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#set(int, java.lang.Object)}.
     */
    @Test
    public void testSet() throws Exception {
        LinkedListRecursive<String> list = new LinkedListRecursive<String>();
        list.add("BEANS");
        list.add("BEANS2");
        list.add("BEANS3");
        list.add("BEANS4");
        assertEquals("BEANS4", list.set(3, "BEANSBEANSBEANSBEANS"));
//        for(int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
        assertEquals("BEANSBEANSBEANSBEANS", list.get(3));
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#contains(java.lang.Object)}.
     */
    @Test
    public void testContains() throws Exception {
        LinkedListRecursive<String> list = new LinkedListRecursive<String>();
        list.add("BEANS");
        list.add("BEANS2");
        list.add("BEANS3");
        list.add("BEANS4");
        assertTrue(list.contains("BEANS3"));
    }


}
