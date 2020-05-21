package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Array Queue tests
 * 
 * @author Nicholas Loftin nlloftin@ncsu.edu
 * @author sanjana cheerla
 *
 */
public class ArrayQueueTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.ArrayQueue#ArrayQueue(int)}.
	 */
	@Test
	public void testArrayQueue() {
		ArrayQueue<String> test = new ArrayQueue<String>(5);
		test.enqueue("Whoo");
		test.enqueue("Whap");
		assertTrue(test instanceof ArrayQueue);

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.ArrayQueue#enqueue(java.lang.Object)}.
	 */
	@Test
	public void testEnqueue() {
		ArrayQueue<String> test = new ArrayQueue<String>(5);
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
	 * {@link edu.ncsu.csc216.pack_scheduler.util.ArrayQueue#dequeue()}.
	 */
	@Test
	public void testDequeue() {
		ArrayQueue<String> test = new ArrayQueue<String>(5);
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
	 * {@link edu.ncsu.csc216.pack_scheduler.util.ArrayQueue#size()}.
	 */
	@Test
	public void testSize() {
		ArrayQueue<String> test = new ArrayQueue<String>(5);
		test.enqueue("Whoo");
		test.enqueue("Whap");
		assertEquals(2, test.size());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.ArrayQueue#setCapacity(int)}.
	 */
	@Test
	public void testSetCapacity() {
		ArrayQueue<String> test = new ArrayQueue<String>(5);
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
	 * Tests if is empty
	 */
	@Test
	public void testIsEmpty() {
		ArrayQueue<String> test = new ArrayQueue<String>(5);
		assertTrue(test.isEmpty());

	}

}
