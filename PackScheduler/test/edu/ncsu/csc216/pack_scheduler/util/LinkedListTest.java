/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the linked list class
 * 
 * @author sanjanacheerla
 *
 */
public class LinkedListTest {
	/** linked list used for testing */
	private LinkedList<String> ll;

	/** String used for tests */
	private String s0 = "a";

	/** String used for tests */
	private String s1 = "b";

	/** String used for tests */
	private String s2 = "c";

	/** String used for tests */
	private String s3 = "d";

	/** String used for tests */
	private String s4 = "e";

	/** String used for tests */
	private String s5 = "f";

	/**
	 * Before to set up test methods
	 */
	@Before
	public void setUp() {
		ll = new LinkedList<String>();
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.LinkedList#size()}.
	 */
	@Test
	public void testSize() {
		ll = new LinkedList<String>();
		assertEquals(0, ll.size());
		ll.add(s0);
		assertEquals(1, ll.size());
		ll.add(s1);
		assertEquals(2, ll.size());
		ll.add(s2);
		assertEquals(3, ll.size());
		ll.add(s3);
		assertEquals(4, ll.size());
		ll.add(s4);
		assertEquals(5, ll.size());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.LinkedList#LinkedList()}.
	 */
	@Test
	public void testLinkedList() {
		ll = new LinkedList<String>();

		assertNotNull(ll);

		assertEquals(0, ll.size());

		assertEquals(-1, ll.listIterator().previousIndex());
		assertFalse(ll.listIterator().hasPrevious());

		assertFalse(ll.listIterator().hasNext());

		try {
			ll.listIterator().next();
			fail();
			ll.listIterator().previous();
			fail();

		} catch (Exception e) {
			assertNotNull(ll);
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.LinkedList#listIterator(int)}.
	 */
	@Test
	public void testListIteratorInt() {
		LinkedList<String> ll1 = new LinkedList<String>();
		try {
			ll1.listIterator().set(s1);
			fail();
		} catch (IllegalStateException e) {
			assertEquals(0, ll1.size());
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.LinkedList#add(int, java.lang.Object)}.
	 * and get() method in super class
	 */
	@Test
	public void testAddIntE() {
		ll = new LinkedList<String>();
		assertEquals(0, ll.size());
		ll.add(s0);
		assertEquals(1, ll.size());
		ll.add(s1);
		assertEquals(2, ll.size());
		ll.add(s2);
		assertEquals(3, ll.size());
		ll.add(s3);
		assertEquals(4, ll.size());
		ll.add(s4);
		assertEquals(5, ll.size());

		try {
			ll.add(0, s0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(5, ll.size());
		}
		try {
			ll.get(5);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(5, ll.size());
		}
		try {
			ll.add(5, s5);
		} catch (IllegalArgumentException e) {
			assertEquals(5, ll.size());
		}

		assertEquals(s0, ll.get(0));
		assertEquals(s1, ll.get(1));
		assertEquals(s2, ll.get(2));
		assertEquals(s3, ll.get(3));
		assertEquals(s4, ll.get(4));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.LinkedList#set(int, java.lang.Object)}.
	 */
	@Test
	public void testSetIntE() {
		ll = new LinkedList<String>();
		assertEquals(0, ll.size());
		ll.add(s0);
		assertEquals(1, ll.size());
		ll.add(s1);
		assertEquals(2, ll.size());
		ll.add(s2);
		assertEquals(3, ll.size());
		ll.add(s3);
		assertEquals(4, ll.size());
		ll.add(s4);
		assertEquals(5, ll.size());

		assertEquals(s0, ll.get(0));

		try {
			ll.set(0, s1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(s0, ll.get(0));
		}

		try {
			ll.set(5, s5);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(s0, ll.get(0));
		}

		ll.set(0, s5);

		assertEquals(s5, ll.get(0));
	}

	/**
	 * Tests indexOf
	 */
	@Test
	public void testIndexOf() {

		ll.add(0, s1);
		ll.add(1, s2);
		ll.add(2, s3);
		assertEquals(0, ll.indexOf(s1));
		assertEquals(1, ll.indexOf(s2));
		assertEquals(2, ll.indexOf(s3));
	}

	/**
	 * Tests lastIndexOf
	 */
	@Test
	public void testLastIndexOf() {
		ll.add(0, s1);
		ll.add(1, s2);
		ll.add(2, s3);
		ll.add(3, s4);
		ll.add(4, s5);
		assertEquals(0, ll.lastIndexOf(s1));
		assertEquals(2, ll.lastIndexOf(s3));
	}
}
