/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the ArrayStack class
 * 
 * @author nlloftin
 * @author scheerl
 *
 */
public class ArrayStackTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#push(java.lang.Object)}.
	 */
	@Test
	public void testPush() {
		ArrayStack<String> tmp = new ArrayStack<String>(10);

		assertNotNull(tmp);

		tmp.push("a");
		tmp.push("b");
		tmp.push("c");

		assertEquals(3, tmp.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#pop()}.
	 */
	@Test
	public void testPop() {
		ArrayStack<String> tmp = new ArrayStack<String>(10);

		assertNotNull(tmp);

		tmp.push("a");
		tmp.push("b");
		tmp.push("c");

		tmp.pop();
		assertEquals(2, tmp.size());
		tmp.pop();
		assertEquals(1, tmp.size());
		tmp.pop();
		assertEquals(0, tmp.size());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		ArrayStack<String> tmp = new ArrayStack<String>(10);

		assertNotNull(tmp);

		tmp.push("a");
		tmp.push("b");
		tmp.push("c");
		assertFalse(tmp.isEmpty());

		tmp.pop();
		assertEquals(2, tmp.size());
		assertFalse(tmp.isEmpty());
		tmp.pop();
		assertEquals(1, tmp.size());
		assertFalse(tmp.isEmpty());
		tmp.pop();
		assertEquals(0, tmp.size());
		assertTrue(tmp.isEmpty());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#size()}.
	 */
	@Test
	public void testSize() {
		ArrayStack<String> tmp = new ArrayStack<String>(10);
		assertNotNull(tmp);

		tmp.push("a");
		tmp.push("b");
		tmp.push("c");
		assertEquals(3, tmp.size());

		tmp.pop();
		assertEquals(2, tmp.size());
		tmp.pop();
		assertEquals(1, tmp.size());
		tmp.pop();
		assertEquals(0, tmp.size());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#setCapacity(int)}.
	 */
	@Test
	public void testSetCapacity() {
		ArrayStack<String> tmp = new ArrayStack<String>(3);
		assertNotNull(tmp);

		tmp.push("a");
		tmp.push("b");
		tmp.push("c");
		assertEquals(3, tmp.size());

		tmp.setCapacity(20);
		tmp.push("d");
		tmp.push("e");
		tmp.push("f");
		assertEquals(6, tmp.size());
	}

}
