package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * ArrayQueue Class
 * 
 * @author Nicholas Loftin nlloftin@ncsu.edu
 * @author sanjana cheerla
 * @param <E> object
 */
public class ArrayQueue<E> implements Queue<E> {
	/**
	 * Initialization of queue
	 */
	private ArrayList<E> list;
	/**
	 * Capacity of list
	 */
	private int currentCap = 0;

	/**
	 * Constructor
	 * 
	 * @param capacity size of queue
	 * @throws IllegalArgumentException if the size of the queue is greater than or
	 *                                  equal to the capacity of the queue
	 */
	public ArrayQueue(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException();
		}
		list = new ArrayList<E>();
		setCapacity(capacity);
		this.currentCap = capacity;
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
		if (element == null) {
			throw new IllegalArgumentException();
		}
		if (size() == this.currentCap) {
			throw new IllegalArgumentException();
		}
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
		if (cap < 0 || cap < list.size()) {
			throw new IllegalArgumentException();
		}
		this.currentCap = cap;
	}

	/**
	 * Checks if it is empty
	 * 
	 * @return T or F based on result
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
}
