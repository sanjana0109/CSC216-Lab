/**
 * @author Nicholas Loftin nlloftin@ncsu.edu
 *
 * Version 1
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Test for the LinkedQueue class
 * 
 * @author Raymond Dong
 * @author Nicholas Loftin nlloftin@ncsu.edu
 * @author sanjana cheerla
 *
 */
public class LinkedQueueTest {

	/**
	 * Test method for creating a linked queue
	 */
	@Test
	public void testLinkedQueue() {
		LinkedQueue<String> test = new LinkedQueue<String>(5);
		test.enqueue("Whoo");
		test.enqueue("Whap");
		assertTrue(test instanceof LinkedQueue);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#enqueue(java.lang.Object)}.
	 */
	@Test
	public void testEnqueue() {
		LinkedQueue<String> test = new LinkedQueue<String>(5);
		test.enqueue("Whoo");
		test.enqueue("Whap");
		test.enqueue("Hah");
		test.enqueue("Ples");
		test.enqueue("aA");
		try {
			test.enqueue("Nope");
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		assertEquals("Whoo", test.dequeue());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#dequeue()}.
	 */
	@Test
	public void testDequeue() {
		LinkedQueue<String> test = new LinkedQueue<String>(5);
		test.enqueue("Whoo");
		test.enqueue("Whap");
		assertEquals("Whoo", test.dequeue());
		assertEquals("Whap", test.dequeue());
		try {
			test.dequeue();
		} catch (NoSuchElementException e) {
			assertNull(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#size()}.
	 */
	@Test
	public void testSize() {
		LinkedQueue<String> test = new LinkedQueue<String>(5);
		test.enqueue("Whoo");
		test.enqueue("Whap");
		assertEquals(2, test.size());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#setCapacity(int)}.
	 */
	@Test
	public void testSetCapacity() {
		LinkedQueue<String> test = new LinkedQueue<String>(5);
		test.enqueue("Whoo");
		test.enqueue("Whap");
		test.setCapacity(10);
		assertEquals(2, test.size());
		try {
			test.setCapacity(1);
		} catch (IllegalArgumentException e) {
			assertEquals(2, test.size());
			assertNull(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		LinkedQueue<String> test = new LinkedQueue<String>(5);
		assertTrue(test.isEmpty());
	}

}
