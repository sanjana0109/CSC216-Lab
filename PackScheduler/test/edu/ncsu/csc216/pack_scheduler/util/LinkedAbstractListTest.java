package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test Class for custom LinkedAbstractLis class
 *
 * @author sggephar
 * @author scheerl
 * @author mbabb
 */
public class LinkedAbstractListTest {

	/**
	 * Tests the size method along with add and size
	 */
	@Test
	public void testSize() {
		var tmp = new LinkedAbstractList<String>(99);

		tmp.add(0, "a");
		tmp.add(0, "b");
		tmp.add(1, "c");

		assertEquals(3, tmp.size());
	}

	/**
	 * Tests the LinkedAbstractList constructor along with add
	 */
	@Test
	public void testLinkedAbstractList() {
		var tmp = new LinkedAbstractList<String>(99);

		assertNotNull(tmp);

		tmp.add(0, "a");
		tmp.add(0, "b");
		tmp.add(1, "c");

		assertEquals("b", (tmp.get(0)));
		assertEquals("c", (tmp.get(1)));
		assertEquals("a", (tmp.get(2)));

		LinkedAbstractList<String> tmp1 = null;
		try {
			tmp1 = new LinkedAbstractList<String>(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(tmp1);
		}
	}

	/**
	 * Tests the LinkedAbstractList setCapacity() method along with add and size
	 */
	@Test
	public void testSetCapacity() {
		var tmp = new LinkedAbstractList<String>(3);
		tmp.add(0, "a");
		tmp.add(0, "b");
		tmp.add(0, "c");

		assertEquals("c", (tmp.get(0)));
		assertEquals("b", (tmp.get(1)));
		assertEquals("a", (tmp.get(2)));

		tmp.setCapacity(4);
		tmp.add(0, "d");
		assertEquals("d", (tmp.get(0)));
		assertEquals("c", (tmp.get(1)));
		assertEquals("b", (tmp.get(2)));
		assertEquals("a", (tmp.get(3)));

		try {
			tmp.setCapacity(3);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, tmp.size());
		}
	}

	/**
	 * Tests the LinkedAbstractList setInt method along with add and size
	 */
	@Test
	public void testSetIntE() {
		var tmp = new LinkedAbstractList<String>(5);
		tmp.add(0, "a");
		tmp.add(0, "b");
		tmp.add(0, "c");

		assertEquals("c", (tmp.get(0)));
		assertEquals("b", (tmp.get(1)));
		assertEquals("a", (tmp.get(2)));

		tmp.set(0, "d");
		tmp.set(1, "e");
		tmp.set(2, "f");

		assertEquals("d", (tmp.get(0)));
		assertEquals("e", (tmp.get(1)));
		assertEquals("f", (tmp.get(2)));

		try {
			tmp.set(6, "d");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, tmp.size());
		}
		try {
			tmp.set(0, "d");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(3, tmp.size());
		}
		try {
			tmp.add(4, "d");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(3, tmp.size());
		}
	}

	/**
	 * Tests the LinkedAbstractList remove method along with add and size
	 */
	@Test
	public void testRemoveInt() {
		var tmp = new LinkedAbstractList<String>(3);
		tmp.add(0, "a");
		tmp.add(0, "b");
		tmp.add(0, "c");

		assertEquals("c", (tmp.get(0)));
		assertEquals("b", (tmp.get(1)));
		assertEquals("a", (tmp.get(2)));

		tmp.remove(1);

		assertEquals("c", (tmp.get(0)));
		assertEquals("a", (tmp.get(1)));

		try {
			tmp.remove(4);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, tmp.size());
		}
	}

}
