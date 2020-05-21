package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Sorted List Class
 * 
 * @author scheerl
 * @author waclem
 * @author znchang
 *
 */
public class SortedListTest {
	/** Test letter A */
	private static final String A = "a";
	/** Test letter B */
	private static final String B = "b";
	/** Test letter C */
	private static final String C = "c";
	/** Test letter D */
	private static final String D = "d";
	/** Test letter E */
	private static final String E = "e";
	/** Test letter F */
	private static final String F = "f";
	/** Test letter G */
	private static final String G = "g";
	/** Test letter H */
	private static final String H = "h";
	/** Test letter I */
	private static final String I = "i";
	/** Test letter J */
	private static final String J = "j";
	/** Test letter K */
	private static final String K = "k";
	/**
	 * Test basic functionality of the SortedList class
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		
		list.add(A);
		list.add(B);
		list.add(C);
		list.add(D);
		list.add(E);
		list.add(F);
		list.add(G);
		list.add(H);
		list.add(I);
		list.add(J);
		assertEquals(10, list.size());
		assertTrue(list.contains(A));
		assertTrue(list.contains(B));
		assertTrue(list.contains(C));
		assertTrue(list.contains(D));
		assertTrue(list.contains(E));
		assertTrue(list.contains(F));
		assertTrue(list.contains(G));
		assertTrue(list.contains(H));
		assertTrue(list.contains(I));
		assertTrue(list.contains(J));
		list.add(K);
		assertEquals(11, list.size());
		assertTrue(list.contains(K));
	}

	/**
	 * Test the add method in SortedList
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		list.add(A);
		assertEquals(2, list.size());
		assertEquals(A, list.get(0));
		assertEquals("banana", list.get(1));
		
		list.add("ab");
		assertEquals(3, list.size());
		assertEquals(A, list.get(0));
		assertEquals("ab", list.get(1));
		assertEquals("banana", list.get(2));
		
		list.add(D);
		assertEquals(4, list.size());
		assertEquals(A, list.get(0));
		assertEquals("ab", list.get(1));
		assertEquals("banana", list.get(2));
		assertEquals(D, list.get(3));
		

		try {
			list.add(null);
			fail();
		}
		catch(NullPointerException e) {
			assertEquals(4, list.size());
		}
                            
		try {
			list.add(A);
			fail();
		}
		catch(IllegalArgumentException e) {
			assertEquals(4, list.size());
		}
	}
	
	/**
	 * Test the get method in SortedList
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.

		try {
			list.get(0);
			fail();
		}
		catch(IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		list.add(A);
		list.add(B);
		list.add(C);
		
		try {
			list.get(-1);
			fail();
		}
		catch(IndexOutOfBoundsException e) {
			assertEquals(3, list.size());
		}
		
		try {
			list.get(3);
			fail();
		}
		catch(IndexOutOfBoundsException e) {
			assertEquals(3, list.size());
		}
		
	}
	
	/**
	 * Test the remove method in SortedList
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		try {
			list.remove(0);
			fail();
		}
		catch(IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		list.add(A);
		list.add(B);
		list.add(C);
		list.add(D);
		
		try {
			list.remove(-1);
			fail();
		}
		catch(IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		
		try {
			list.remove(4);
			fail();
		}
		catch(IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		
		list.remove(2);
		assertEquals(3, list.size());
		
		list.remove(list.size() - 1);
		assertEquals(2, list.size());
		
		list.remove(0);
		assertEquals(1, list.size());
		
		list.remove(0);
		assertEquals(0, list.size());
	}
	
	/**
	 * Test the indexOf method in SortedList
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		list.indexOf(A);
		assertEquals(list.indexOf(A), -1);
		
		list.add(A);
		list.add(B);
		list.add(C);
		list.add(D);
		
		list.indexOf(E);
		assertEquals(list.indexOf(E), -1);
		
		list.indexOf(A);
		assertEquals(list.indexOf(A), 0);

		
		list.indexOf(B);
		assertEquals(list.indexOf(B), 1);
		
		
		list.indexOf(C);
		assertEquals(list.indexOf(C), 2);
		
		
		list.indexOf(D);
		assertEquals(list.indexOf(D), 3);
		
		
		try {
			list.indexOf(null);
			fail();
		}
		catch(NullPointerException e) {
			assertEquals(4, list.size());
		}
		
	}
	
	/**
	 * Test the clear method in SortedList
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		list.add(A);
		list.add(B);
		list.add(C);
		list.add(D);
		assertEquals(4, list.size());
		
		list.clear();
		assertEquals(0, list.size());

	}

	/**
	 * Test the isEmpty method in SortedList
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		assertEquals(list.isEmpty(), true);
		
		list.add(A);
		assertEquals(list.isEmpty(), false);

	}

	/**
	 * Test the contains method in SortedList
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();

		list.contains(A);
		assertEquals(list.contains(A), false);
		
		list.add(A);
		list.add(B);
		list.add(C);
		list.add(D);
		
		list.contains(A);
		assertEquals(list.contains(A), true);
		list.contains(B);
		assertEquals(list.contains(B), true);
		list.contains(C);
		assertEquals(list.contains(C), true);
		list.contains(D);
		assertEquals(list.contains(D), true);
		
		list.contains(E);
		assertEquals(list.contains(E), false);
	}
	
	/**
	 * Test the equals method in SortedList
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		list1.add(A);
		list1.add(B);
		list1.add(C);
		list1.add(D);
		
		list2.add(A);
		list2.add(B);
		list2.add(C);
		list2.add(D);
		
		list3.add(E);
		list3.add(F);
		list3.add(G);

		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		
		assertFalse(list1.equals(list3));
		assertFalse(list3.equals(list1));
		
	}
	
	/**
	 * Test the hashCode method in SortedList
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		
		list1.add(A);
		list1.add(B);
		list1.add(C);
		list1.add(D);
		
		list2.add(A);
		list2.add(B);
		list2.add(C);
		list2.add(D);
		
		list3.add(E);
		list3.add(F);
		list3.add(G);
		
		assertEquals(list2.hashCode(), list1.hashCode());
		
		assertNotEquals(list1.hashCode(), list3.hashCode());
	}

}
 
