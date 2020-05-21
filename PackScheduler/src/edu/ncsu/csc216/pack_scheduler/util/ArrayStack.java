package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * ArrayStack, implements a stack data type as an Array. Holds the 5 generic
 * overwritten stack methods, push, pop, isEmpty, size, and setCapacity.
 * 
 * @author nlloftin
 * @author scheerl
 *
 * @param <E> e generic type
 */
public class ArrayStack<E> implements Stack<E> {
	/**
	 * Stack instance
	 */
	private ArrayList<E> stack;
	/**
	 * Stack capacity
	 */
	private int stackCap;

	/**
	 * Constructor for ArrayStack
	 * 
	 * @param capacity size of stack
	 * @throws IllegalArgumentException if capacity is less than 0 or less than
	 *                                  current size
	 */
	public ArrayStack(int capacity) {
		stack = new ArrayList<E>();
		setCapacity(capacity);
	}

	/**
	 * Adds element to 0 position
	 * 
	 * @param element to add to stack
	 *
	 */
	@Override
	public void push(E element) throws IllegalArgumentException {
		if (element == null || stackCap == stack.size()) {
			throw new IllegalArgumentException();
		}
		stack.add(0, element);

	}

	/**
	 * Removes 0 position
	 * 
	 * @return E element removed
	 */
	@Override
	public E pop() throws EmptyStackException {
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		}
		var tmp = stack.get(0);
		stack.remove(0);
		return tmp;
	}

	/**
	 * Checks if the stack is Empty
	 * 
	 * @return T or F if stack is empty
	 */
	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	/**
	 * Size
	 * 
	 * @return int of size of stack
	 */
	@Override
	public int size() {
		return stack.size();
	}

	/**
	 * Sets Capacity
	 * 
	 * @param capacity capacity
	 * @throws IllegalArgumentException if capacity is less than 0 or less than
	 *                                  current size
	 */
	@Override
	public void setCapacity(int capacity) throws IllegalArgumentException {
		if (capacity < 0 || capacity < stack.size()) {
			throw new IllegalArgumentException();
		}
		this.stackCap = capacity;

	}

}