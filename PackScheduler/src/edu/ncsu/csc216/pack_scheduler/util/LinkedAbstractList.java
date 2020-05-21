package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Implements a custom linked list that does not allow for null elements or
 * duplicate elements.
 *
 * @author sggephar
 * @author scheerl
 * @author mbabb
 *
 * @param <E> generic comparable type E.
 */
public class LinkedAbstractList<E> extends java.util.AbstractList<E> {

    /** front node of the LinkedAbstractList */
    private ListNode front;

    /** front node of the LinkedAbstractList */
    private ListNode back;

    /** size of linked list */
    private int size;

    /** capacity of linked list */
    private int capacity;

    /**
     * Constructs a new LinkedAbstractList object
     *
     * @param capacity of linked list
     * @throws IllegalArgumentException if the capacity is less than or equal to 0.
     */
    public LinkedAbstractList(int capacity) {

        this.front = new ListNode(null);
        setCapacity(capacity);
        this.size = 0;
    }

    /**
     * Sets the capacity of the LinkedAbstractList object
     *
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        if (capacity <= 0 || capacity < size) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
    }

    /**
     * Returns the element at the provided index
     *
     * @return element of type E at the provided index
     * @throws IndexOutOfBoundsException if the index is less than 0 or if the index
     *                                   is greater than or equal to the size.
     */
    @Override
    public E get(int idx) {
        if (idx < 0 || idx > this.size - 1) {
            throw new IndexOutOfBoundsException();
        }

        var currentNode = this.front;
        int i = 0;

        while (currentNode.next != null) {
            if (i == idx) {
                return currentNode.data;
            }
            currentNode = currentNode.next;
            i++;
        }

        return null;
    }

    /**
     * Replaces the old element at the provided index with the new provided element
     * at the provided index.
     *
     * @param idx     of the element being replaced
     * @param element replacing old element at index
     * @return the element being replaced/overwritten at the index.
     * @throws IllegalArgumentException  if size is equal to capacity and if the
     *                                   element being added is a duplicate element
     *                                   already in the LinkedAbstractList.
     * @throws NullPointerException      if the element being added is null.
     * @throws IndexOutOfBoundsException if the index is less than 0 or if the index
     *                                   is greater than or equal to the size.
     */
    @Override
    public E set(int idx, E element) {
        if (idx < 0 || idx > this.size - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (element == null) {
            throw new NullPointerException();
        }
        if (this.isDuplicate(element)) {
            throw new IllegalArgumentException();
        }
        var currentNode = this.front;

        for (int i = 0; i < idx; i++) {
            currentNode = currentNode.next;
        }

        var data = currentNode.data;
        currentNode.data = element;

        return data;
    }

    /**
     * Adds an Element to the LinkedAbstractList at the provided index
     *
     * @param idx     index of the element being added to the LinkedAbstractList
     * @param element the element being added to the LinkedAbstractList
     * @throws IllegalArgumentException  if size is equal to capacity and if the
     *                                   element being added is a duplicate element
     *                                   already in the LinkedAbstractList.
     * @throws NullPointerException      if the element being added is null.
     * @throws IndexOutOfBoundsException if the index is less than 0 or if the index
     *                                   is greater than the size.
     */
    @Override
    public void add(int idx, E element) {
        if (this.isDuplicate(element) || this.size == this.capacity) {
            throw new IllegalArgumentException();
        }
        if (idx < 0 || idx > this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (element == null) {
            throw new NullPointerException();
        }
        var currentNode = this.front;
        ListNode prevNode = null;

        for (int i = 0; i < idx; i++) {
            prevNode = currentNode;
            currentNode = currentNode.next;
        }

        var tmpNode = new ListNode(element, currentNode);

        if (idx == 0) {
            this.front = tmpNode;
            this.back = tmpNode;
        } else {
            prevNode.next = tmpNode;
        }
        
        this.back = this.front;
        while(this.back != null) {
        	this.back = this.back.next;
        }
        
        this.size++;
    }

    /**
     * Removes an element at the provided index and returns that removed element.
     *
     * @param idx of the element being removed
     * @return E element of what has been removed.
     * @throws IndexOutOfBoundsException if the index is less than 0 or if the index
     *                                   is greater than or equal to the size.
     */
    @Override
    public E remove(int idx) {
        if (idx < 0 || idx >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        if (idx == 0) {
            var tmpNode = this.front;
            this.front = this.front.next;
            this.size--;
            return tmpNode.data;
        }

        ListNode currNode = this.front;
        ListNode tmpNode = null;

        for (int i = 0; i < idx - 1; i++) {
            currNode = currNode.next;
        }
        tmpNode = currNode;
        this.back = tmpNode.next;
        var data = tmpNode.next.data;
        currNode.next = currNode.next.next;
        this.size--;

        return data;
    }

    /**
     * Returns the size of the LinkedAbstractList
     *
     * @return size of the LinkedAbstractList
     */
    @Override
    public int size() {
        var sz = 0;
        var node = this.front;

        while (node.next != null) {
            sz++;
            node = node.next;
        }

        this.size = sz;
        return this.size;
    }

    /**
     * Checks to see if provided element is already in the LinkedAbstractList
     *
     * @param element being checked to see if its a duplicate
     */
    private boolean isDuplicate(E element) {
        var currNode = this.front;

        while (currNode.next != null) {
            if (currNode.data.equals(element)) {
                return true;
            }
            currNode = currNode.next;
        }
        return false;
    }

    /**
     * Handles List nodes of LinkedAbstractList
     *
     * @author sggephar
     * @author scheerl
     * @author mbabb
     */
    private class ListNode {

        /** data contained in the node */
        private E data;

        /** next node in the list */
        private ListNode next;

        /**
         * Constructs a new ListNode with only type data.
         *
         * @param data of the ListNode being constructed
         */
        public ListNode(E data) {
            this.data = data;
            this.next = null;
        }

        /**
         * Constructs a new ListNode with type data and the next Node in the list.
         *
         * @param data of the ListNode being constructed
         * @param next node of the ListNode being constructed
         */
        public ListNode(E data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }
}