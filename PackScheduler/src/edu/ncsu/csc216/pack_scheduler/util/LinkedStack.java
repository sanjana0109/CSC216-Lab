package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * LinkedStack, implements a stack data type as a Linked List. Holds the 5
 * generic overwritten stack methods, push, pop, isEmpty, size, and setCapacity.
 * A stack made of nodes
 * 
 * @author nlloftin
 * @author scheerl
 * @author rdong3
 *
 * @param <E> e generic type
 */
public class LinkedStack<E> implements Stack<E> {
	/**
	 * Stack instance
	 */
	private LinkedAbstractList<E> stack;

	/**
	 * Constructor for ArrayStack
	 * 
	 * @param capacity size of linked stack
	 * @throws IllegalArgumentException if capacity is less than 0 or less than
	 *                                  current size
	 */
	public LinkedStack(int capacity) {
	    stack = new LinkedAbstractList<E>(capacity);
	    setCapacity(capacity);
	}

	/**
	 * Pushes an element onto the stack.
	 * 
	 * @param element to be pushed.
	 * @throws IllegalArgumentException if capacity has been reached
	 */
	@Override
	public void push(E element) throws IllegalArgumentException {
        stack.add(0, element);
	}

	/**
	 * Pops, (Removes) an element from the stack.
	 * 
	 * @return the element which was popped.
	 * @throws EmptyStackException if the stack is empty.
	 */
	@Override
	public E pop() throws EmptyStackException {
	    if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        E tmp = stack.get(0);
        stack.remove(0);
        return tmp;
	}

	/**
	 * Checks to see if the stack is empty.
	 * 
	 * @return true if empty, false if not empty.
	 */
	@Override
	public boolean isEmpty() {
	    return stack.isEmpty();
	}

	/**
	 * Returns the size of the stack.
	 * 
	 * @return the size of the stack.
	 */
	@Override
	public int size() {
	    return stack.size();
	}

	/**
	 * Sets the capacity of the stack
	 * 
	 * @param capacity to be set
	 * @throws IllegalArgumentException if capacity is negative, or if capacity is
	 *                                  less than the number of elements in the
	 *                                  stack.
	 */
	@Override
	public void setCapacity(int capacity) throws IllegalArgumentException {
		stack.setCapacity(capacity);
	}
}
