package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;
import java.util.Arrays;

/**
 * Implements a custom array list that does not allow for null elements or
 * duplicate elements.
 *
 * @author sggephar
 * @author scheerl
 * @author mbabb
 *
 * @param <E> generic comparable type E.
 */
public class ArrayList<E> extends AbstractList<E>
{

    /** A constant value for initializing the list size */
    private static final int INIT_SIZE = 10;

    /** An array of type E */
    private E[] list;

    /** The size of the list */
    private int size;

    /** The capacity of the array list */
    private int capacity;

    /**
     * Constructs the custom array list
     */
    @SuppressWarnings("unchecked") public ArrayList()
    {
        setCapacity(INIT_SIZE);
        setSize(0);
        this.list = (E[]) (new Object[INIT_SIZE]);
    }

    /**
     * Sets the size of the array list
     *
     * @param size the size to set
     */
    private void setSize(int size)
    {
        if (size < 0) {
            throw new IllegalArgumentException();
        }
        if (size > capacity) {
            growArray();
        }
        this.size = size;
    }

    /**
     * sets the capacity of the array list
     *
     * @param capacity the capacity to set
     */
    private void setCapacity(int capacity)
    {
        if (capacity < 0 || capacity < size) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
    }

    /**
     * adds an element to the array list at a specific index
     *
     * @param index   where element will be added
     * @param element to add at specific element
     */
    @Override public void add(int index, E element)
    {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (element == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < size; i++) {
            if (list[i].equals(element)) {
                throw new IllegalArgumentException();
            }
        }
        if (size == capacity) {
            growArray();
        }
        for (int i = size; i > index; i--) {
            Sorting.swap(list, i, i - 1);
        }
        this.size++;
        list[index] = element;
    }

    /**
     * increases capacity of the array
     */
    private void growArray()
    {
        setCapacity((int) (Math.E * capacity));
        list = Arrays.copyOf(list, this.capacity);
    }

    /**
     * removes an element from the specific index of the array
     *
     * @param index of the element to be removed
     */
    @Override public E remove(int index)
    {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        E elem = list[index];
        if (index == size) {
            size--;
            return elem;
        }
        for (int i = index; i < size; i++) {
            Sorting.swap(list, i, i + 1);
        }
        size--;

        return elem;
    }

    /**
     * element at the specified index is replaced with the given element.
     *
     * @param index   of the element to be set/returned
     * @param element to be set at a specific index
     * @return the element set at the given location
     */
    @Override public E set(int index, E element)
    {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        if (element == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < size; i++) {
            if (list[i].equals(element)) {
                throw new IllegalArgumentException();
            }
        }
        var item = list[index];
        list[index] = element;
        return item;
    }

    /**
     * Returns the element at a specified index
     *
     * @param index of the element to return
     * @return the element at the specified index
     */
    @Override public E get(int index)
    {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return list[index];
    }

    /**
     * Returns the size of the array list
     *
     * @return size of the array list
     */
    @Override public int size()
    {
        return size;
    }
}
