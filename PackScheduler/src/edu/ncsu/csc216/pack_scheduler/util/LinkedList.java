package edu.ncsu.csc216.pack_scheduler.util;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * 
 * This class is a linked list class that uses 2 internal classes, node and
 * iterator. Extends abstract sequential list class.
 * 
 * @author sanjana cheerla
 * 
 * @param <E> generic parameter
 *
 */
public class LinkedList<E> extends java.util.AbstractSequentialList<E> {

	/** front node of the list */
	private ListNode front;

	/** back of the list */
	private ListNode back;

	/** size of the list */
	private int size;

	/**
	 * Constructs a new linked list
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null, front, null);
		front.next = back;
		size = 0;
	}

	/**
	 * Creates a new list iterator object with the provided parameter
	 * 
	 * @param index of the current node
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than
	 *                                   size
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		LinkedListIterator l = new LinkedListIterator(index);
		return l;
	}

	/**
	 * Adds an element to the list at the provided index
	 * 
	 * @param index   of the element being added
	 * @param element being added
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than
	 *                                   size
	 * @throws IllegalArgumentException  if the element already exists in the list
	 */
	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();

		}
		for (int i = 0; i < size; i++) {
			if (get(i).equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		LinkedListIterator l = new LinkedListIterator(index);
		l.add(element);
	}

	/**
	 * sets an element to the list at the provided index
	 * 
	 * @param index   of the element being set
	 * @param element being set
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than
	 *                                   or equal to the size
	 * @throws IllegalArgumentException  if the element already exists in the list
	 * @return the element that was replaced
	 */
	@Override
	public E set(int index, E element) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();

		}
		for (int i = 0; i < size; i++) {
			if (get(i).equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		LinkedListIterator l = new LinkedListIterator(index);

		E elementBeingReplaced = l.next();
		l.set(element);
		return elementBeingReplaced;
	}

	/**
	 * Returns the size of the list
	 * 
	 * @return size of the list
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Handles List nodes of LinkedAList
	 * 
	 * @author sanjana cheerla
	 */
	private class ListNode {

		/** data contained in the node */
		private E data;

		/** next node in the list */
		private ListNode next;

		/** previous node in list */
		private ListNode prev;

		/**
		 * Constructs a new ListNode with only type data.
		 *
		 * @param data of the ListNode being constructed
		 */
		private ListNode(E data) {
			this.data = data;
			this.next = null;
			this.next = null;
		}

		/**
		 * Constructs a new list with the next, previous and provided data
		 * 
		 * @param data of the ListNode being constructed
		 * @param next node of the list
		 * @param prev node of the list
		 */
		private ListNode(E data, ListNode next, ListNode prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}

	/**
	 * The internal class of Linked List, this class is an iterator of the linked
	 * list and represents the current node of the linked list
	 * 
	 * @author sanjana cheerla
	 *
	 */
	private class LinkedListIterator implements ListIterator<E> {

		/** represents the ListNode that would be returned on a call to previous() */
		ListNode previous;

		/** represents the ListNode that would be returned on a call to next() */
		ListNode next;

		/** the index that would be returned on a call to previousIndex() */
		int previousIndex;

		/** the index that would be returned on a call to nextIndex() */
		int nextIndex;

		/**
		 * represents the ListNode that was returned on the last call to either
		 * previous() or next() or null if a call to previous() or next() was not the
		 * last call on the ListIterator.
		 */
		ListNode lastRetrieved;

		/**
		 * Creates a new linked list iterator object at the provided index
		 * 
		 * @param index of the current node
		 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than
		 *                                   size
		 */
		public LinkedListIterator(int index) {
			if (index < 0 || index > size()) {
				throw new IndexOutOfBoundsException();
			}
			previous = front;
			next = previous.next;
			previousIndex = index - 1;
			nextIndex = index;

			if (index != 0) {
				for (int i = 0; i < index; i++) {
					previous = next;
					next = next.next;
				}
			}

			lastRetrieved = null;
		}

		/**
		 * Checks to see if there is a next node
		 * 
		 * @return true if there is a next node with data, false other wise
		 */
		@Override
		public boolean hasNext() {
			if (next.data != null)
				return true;
			return false;
		}

		/**
		 * Returns the next data in the linked list and moves to the next element in the
		 * list
		 * 
		 * @throws NoSuchElementException if there is no next data
		 * @return the data of the next node
		 */
		@Override
		public E next() {
			if (next.data == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = next;
			previousIndex++;
			nextIndex++;
			next = next.next;
			return lastRetrieved.data;
		}

		/**
		 * Checks to see if there is a previous node
		 * 
		 * @return true if there is a previous node with data, false other wise
		 */
		@Override
		public boolean hasPrevious() {
			if (previous.data == null)
				return false;
			return true;
		}

		/**
		 * Returns the previous data in the linked list and moves to the next element in
		 * the list
		 * 
		 * @throws NoSuchElementException if there is no previous data
		 * @return the data of the previous node
		 */
		@Override
		public E previous() {
			if (previous.data == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = previous;
			previousIndex--;
			nextIndex--;
			previous = previous.prev;
			return previous.data;
		}

		/**
		 * Returns the next index of the linked list
		 */
		@Override
		public int nextIndex() {
			if (next == null)
				return size;
			return nextIndex - 1;
		}

		/**
		 * Returns the previous index of the linked list
		 */
		@Override
		public int previousIndex() {
			if (previous == null)
				return -1;
			return previousIndex;
		}

		/**
		 * Remove() method will remove the element returned by the last call to
		 * previous() or next()
		 * 
		 * @throws IllegalStateException if the last retrieved object is null
		 */
		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			lastRetrieved.prev.next = next;
			next = next.next;

			size--;
		}

		/**
		 * Sets the last retrieved node data to the parameter e
		 * 
		 * @param e element being set to the last retrieved node
		 * @throws IllegalStateException if the last retrieved object is null
		 * @throws NullPointerException  if e is null
		 */
		@Override
		public void set(E e) {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			if (e == null) {
				throw new NullPointerException();
			}
			lastRetrieved.data = e;

		}

		/**
		 * Adds the element e
		 * 
		 * @param e the element being added
		 * @throws NullPointerException if e is null
		 */
		@Override
		public void add(E e) {
			if (e == null) {
				throw new NullPointerException();
			}
			ListNode newNode = new ListNode(e);
			next.prev = newNode;
			newNode.next = next;
			newNode.prev = previous;

			previous.next = newNode;

			size++;
			lastRetrieved = null;
		}
	}
}
