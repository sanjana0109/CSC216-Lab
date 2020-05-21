/**
 * @author Nicholas Loftin nlloftin@ncsu.edu
 *
 * Version 1
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * A queue made of nodes
 * 
 * @author Raymond Dong
 * @author Nicholas Loftin nlloftin@ncsu.edu
 * @author sanjana cheerla
 * 
 * @param <E> E generic type
 */
public class LinkedQueue<E> implements Queue<E> {
	/** Initialization of list */
	LinkedAbstractList<E> list;

	/**
	 * Constructor for LinkedQueue class
	 * 
	 * @param capacity size of queue
	 * @throws IllegalArgumentException if the size of the queue is greater than or
	 *                                  equal to the capacity of the queue
	 */
	public LinkedQueue(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
	}

	/**
	 * Adds to queue
	 * 
	 * @param element the element being added
	 * @throws IllegalArgumentException if the size of the queue is greater than or
	 *                                  equal to the capacity of the queue
	 */
	@Override
	public void enqueue(E element) throws IllegalArgumentException {
		list.add(list.size(), element);
	}

	/**
	 * Removes the element at the front of the queue
	 * 
	 * @return the element being removed
	 * @throws NoSuchElementException if the queue is empty
	 */
	@Override
	public E dequeue() throws NoSuchElementException {
		if (list.isEmpty()) {
			throw new NoSuchElementException();
		}
		E temp = list.get(0);
		list.remove(0);
		return temp;
	}

	/**
	 * Gets the size of the queue
	 * 
	 * @return the size of the queue
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the capacity of the queue
	 * 
	 * @param cap the number the capacity is being set to
	 * @throws IllegalArgumentException if the cap is greater than size or negative
	 *                                  or 0
	 */
	@Override
	public void setCapacity(int cap) throws IllegalArgumentException {
		list.setCapacity(cap);
	}

	/**
	 * Checks if the list is empty
	 * 
	 * @return true if the size of the queue is 0
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

}
