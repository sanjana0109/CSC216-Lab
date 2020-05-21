package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Queue basic interface for Array Queue
 * @author Nicholas Loftin nlloftin@ncsu.edu
 * Version 1
 * @param <E> e object
 */
public interface Queue<E> {
	/**
	 * Adds the element to the back of the queue
	 * @throws IllegalArgumentException if capacity has been reached
	 * @param element to add to queue
	 */
	void enqueue(E element) throws IllegalArgumentException;
	/**
	 * Removes and returns element in front of queue
	 * @throws NoSuchElementException if queue is empty
	 * @return object removed 
	 */
	E dequeue() throws NoSuchElementException;
	/**
	 * Returns size of queue
	 * @return size of queue
	 */
	int size();
	/**
	 * Sets capacity
	 *  @param cap input to set
	 *  @throws IllegalArgumentException if negative, 
	 *  or less than current size
	 */
	void setCapacity(int cap) throws IllegalArgumentException;
	/**
	 * Checks if queue is empty
	 * @return T or F based on if it is empty
	 */
	boolean isEmpty();
}
