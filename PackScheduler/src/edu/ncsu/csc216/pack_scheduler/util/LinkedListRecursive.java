/**
 *LinkedLIstCRecursive.java
 * @author Nicholas Loftin nlloftin@ncsu.edu
 * Version 1
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * TODO
 * 
 * @author Nicholas Loftin nlloftin@ncsu.edu
 * @author sanjana cheerla
 * @param <E> generic type
 */
public class LinkedListRecursive<E> {
	/**
	 * size field
	 */
	private int size = size();
	/**
	 * Current head of list
	 */
	private ListNode head;

	/**
	 * Blank constructor class
	 */
	public LinkedListRecursive() {
		head = null;
		size = 0;
	}

	/**
	 * Checks if is empty
	 * 
	 * @return T or F based on size
	 */
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	/**
	 * Gets size
	 * 
	 * @return size of list
	 */
	public int size() {
		ListNode temp = head;
		int c = 0;
		while (temp != null) {
			c++;
			temp = temp.next;
		}
		return c;
	}

	/**
	 * Default add with no index
	 * 
	 * @param value element to add
	 * @return true or false if element added
	 * @throws IllegalArgumentException if element is already in list
	 */
	public boolean add(E value) {

		if (value == null) {
			throw new NullPointerException();
		} else if (contains(value)) {
			throw new IllegalArgumentException("Element already in list");
		}
		if (isEmpty()) {
			size++;
			head = new ListNode(value, null);
			return true;
		} else {
			size++;
			return head.add(value);
		}

	}

	/**
	 * Add but with index
	 * 
	 * @param in    index
	 * @param value element
	 * @throws IllegalArgumentException if element already is in list
	 * @throws NullPointerException if head is not null and contains value, 
	 * or if the value is null 
	 * @throws IndexOutOfBoundsException if index is invalid
	 */
	public void add(int in, E value) {
		if (value == null) {
			throw new NullPointerException();
		} else if (contains(value)) {
			throw new IllegalArgumentException("Element already in list");
		} else if (in < 0 || in > size) {
			throw new IndexOutOfBoundsException();
		} else if (head != null && head.contains(value)) {
			throw new NullPointerException();
		}
		if (in == 0) {
			head = new ListNode(value, head);
			size++;
		} else {
			head.add(in - 1, value);
		}
	}

	/**
	 * Gets at index
	 * 
	 * @param in index
	 * @return Element at index
	 * @throws IndexOutOfBoundsException if index is invalid
	 */
	public E get(int in) {
		if (in < 0 || in >= size)
			throw new IndexOutOfBoundsException();
		if (in == 0)
			return head.data;
		else
			return head.get(in);
	}

	/**
	 * Removes at index
	 * 
	 * @param in index
	 * @return element removed
	 * @throws IndexOutOfBoundsException if index is invalid
	 */
	public E remove(int in) {
		if (in < 0 || in >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (in == 0) {
			E tempVal = head.data;
			head = head.next;
			size--;
			return tempVal;

		} else {
			return head.remove(in - 1);
		}
	}

	/**
	 * Removes by element instead of index
	 * 
	 * @param e element to remove
	 * @return T or F if the element is removed successfully
	 */
	public boolean remove(E e) {
		if (head != null) {
			if (head.data.equals(e)) {
				head = head.next;
				size--;
				return true;
			} else {
				return head.remove(e);
			}
		}
		return false;
	}

	/**
	 * Sets element at index
	 * 
	 * @param in index
	 * @param e  element
	 * @return element that was originally at index
	 * @throws IllegalArgumentException if element already is in list
     * @throws NullPointerException if head is not null and contains value, 
     * or if the value is null 
     * @throws IndexOutOfBoundsException if index is invalid
	 */
	public E set(int in, E e) {
		if (in < 0 || in >= size)
			throw new IndexOutOfBoundsException();
		if (e == null)
			throw new NullPointerException();
		if (this.contains(e))
			throw new IllegalArgumentException();
		if (in == 0) {
			E returned = head.data;
			head.data = e;
			return returned;
		} else {
			return head.set(in - 1, e);
		}
	}

	/**
	 * Checks list if it contains a certain value
	 * 
	 * @param value value to search for
	 * @return T or F based on if found in list
	 */
	public boolean contains(E value) {
		if (!this.isEmpty()) {
			return head.contains(value);
		} else {
			return false;
		}
	}

	/**
	 * List Node Inner class
	 * 
	 * @author sanjana cheerla
     * @author Nicholas Loftin
	 *
	 */
	private class ListNode {
		/**
		 * Data in current node
		 */
		private E data;
		/**
		 * Next node
		 */
		private ListNode next;

		/**
		 * Add to list
		 * 
		 * @param in index
		 * @param e  element to add
		 */
		private void add(int in, E e) {
			int location = 0;
			if (location == in) {
				this.next = new ListNode(e, this.next);
				size++;
			} else {
				in--;
				this.next.add(in, e);
			}
		}

		/**
		 * Gets at index
		 * 
		 * @param in index
		 * @return Value at index
		 */
		private E get(int in) {
			if (in == 1)
				return next.data;
			else
				return next.get(--in);
		}

		/**
		 * Removes value at index
		 * 
		 * @param in index
		 * @return element removed
		 */
		private E remove(int in) {
			int location = 0;
			if (next != null) {
				if (location == in) {
					ListNode returned = next;
					next = next.next;
					size--;
					return returned.data;
				}
				if (next != null)
					next.remove(--in);
			}
			return null;
		}

		/**
		 * Sets value at given index
		 * 
		 * @param in index
		 * @param e  element
		 * @return value that was originally at that index
		 */
		private E set(int in, E e) {
			int location = 0;
			if (location == in) {
				E returned = next.data;
				next.data = e;
				return returned;
			} else {
				return next.set(--in, e);
			}
		}

		/**
		 * Removes element from list based on item not index
		 * 
		 * @param e element to search for
		 * @return T or F if the element was removed
		 */
		private boolean remove(E e) {
			if (this.contains(e)) {
				if (next != null) {
					if (this.next.data.equals(e)) {
						this.next = this.next.next;
						return true;
					}
					return this.next.remove(e);
				} else
					return false;
			} else
				return false;
		}

		/**
		 * Checks if contains element
		 * 
		 * @param e element to search
		 * @return true or false if the list contains the element
		 */
		private boolean contains(E e) {
			if (this.data.equals(e)) {
				return true;
			}
			if (this.next == null) {
				return false;
			}
			return this.next.contains(e);

		}

		/**
		 * Adds element to end of list
		 * 
		 * @param e element to add
		 * @return T or F based on if element was added
		 */
		private boolean add(E e) {
			if (this.next == null) {
				this.next = new ListNode(e, null);
				return true;
			}
			return this.next.add(e);
		}

		/**
		 * ListNode Constructor
		 * 
		 * @param e    element to add to current node
		 * @param next next element in list
		 */
		private ListNode(E e, ListNode next) {
			this.data = e;
			this.next = next;
		}

	}
}
