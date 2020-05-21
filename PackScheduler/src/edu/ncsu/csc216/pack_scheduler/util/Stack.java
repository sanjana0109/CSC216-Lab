package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Interface for stack. Holds the 5 generic stack methods, push, pop, isEmpty,
 * size, and setCapacity.
 * 
 * @author nlloftin
 * @author scheerl
 *
 * @param <E> e generic type
 */
public interface Stack<E> {

	/**
	 * Pushes an element onto the stack.
	 * 
	 * @param element to be pushed.
	 * @throws IllegalArgumentException if capacity has been reached
	 */
	void push(E element) throws IllegalArgumentException;

	/**
	 * Pops, (Removes) an element from the stack.
	 * 
	 * @return the element which was popped.
	 * @throws EmptyStackException if the stack is empty.
	 */
	E pop() throws EmptyStackException;

	/**
	 * Checks to see if the stack is empty.
	 * 
	 * @return true if empty, false if not empty.
	 */
	boolean isEmpty();

	/**
	 * Returns the size of the stack.
	 * 
	 * @return the size of the stack.
	 */
	int size();

	/**
	 * Sets the capacity of the stack
	 * 
	 * @param capacity to be set
	 * @throws IllegalArgumentException if capacity is negative, or if capacity is
	 *                                  less than the number of elements in the
	 *                                  stack.
	 */
	void setCapacity(int capacity) throws IllegalArgumentException;
}
