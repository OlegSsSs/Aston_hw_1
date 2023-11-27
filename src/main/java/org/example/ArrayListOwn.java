package org.example;

import java.util.Arrays;

/**
 * ArrayList - own implementation ArrayList.
 * Methods include:
 * adding an element
 * adding an element by index
 * getting an element
 * remove element
 * clearing the collection
 * sort bubble
 *
 * @param <E> type of elements in the collection
 * @author OlegS
 */
public class ArrayListOwn<E> {

    /**
     * Capacity on default for elements array
     */
    private static final int DEFAULT_CAPACITY_ELEMENTS = 10;

    /**
     * Array to store elements
     */
    private Object[] arrayData;

    /**
     * Current size of list
     */
    private int size;

    /**
     * Creates an empty list with an initial capacity of 10
     */
    public ArrayListOwn() {
        this.arrayData = new Object[DEFAULT_CAPACITY_ELEMENTS];
        this.size = 0;
    }

    /**
     * Adds an element to the end of the list
     *
     * @param element element to be added to the list
     */
    public void add(E element) {
        checkAndIncreaseCapacity();
        arrayData[size++] = element;
    }

    /**
     * Adds an element for a specific index
     *
     * @param index index at which to add the element
     * @param element element to be added to the list
     */
    public void addByIndex(int index, E element) {
        checkIndex(index);
        checkAndIncreaseCapacity();
        System.arraycopy(arrayData, index, arrayData, index + 1, size - index);
        arrayData[index] = element;
        size++;
    }

    /**
     * Returns the element at the specified index
     *
     * @param index index of the element to return
     * @return element at the specified index
     */
    @SuppressWarnings("unchecked")
    public E getByIndex(int index) {
        checkIndex(index);
        return (E) arrayData[index];
    }

    /**
     * Remove element at the specified index
     *
     * @param index index of the element to remove
     */
    public void removeByIndex(int index) {
        checkIndex(index);
        System.arraycopy(arrayData, index + 1, arrayData, index, size - index - 1);
        arrayData[--size] = null;
    }

    /**
     * Clears the list by remove all elements
     */
    public void clear() {
        Arrays.fill(arrayData, null);
        size = 0;
    }

    /**
     * Returns the number of elements list
     *
     * @return number of elements in the collection
     */
    public int size() {
        return size;
    }

    /**
     * Check if list is full, increases the capacity of the list by x2
     */
    private void checkAndIncreaseCapacity() {
        if (size == arrayData.length) {
            int newCapacity = arrayData.length * 2;
            arrayData = Arrays.copyOf(arrayData, newCapacity);
        }
    }

    /**
     * Checks the specified index is valid for accessing list elements
     *
     * @param index index for check
     * @throws IndexOutOfBoundsException if the specified index is outside the valid range
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        return "List: " + Arrays.toString(Arrays.copyOf(arrayData, size));
    }

    /**
     * Compare elements in the list
     *
     * @param index1 the index of the first element to compare
     * @param index2 the index of the second element to compare
     * @return a negative integer if the first element is less than the second, zero if they are equal,
     * and a positive integer if the first element is greater
     */
    @SuppressWarnings("unchecked")
    private int compareByIndex(int index1, int index2) {
        E element1 = (E) arrayData[index1];
        E element2 = (E) arrayData[index2];
        return Integer.compare(element1.hashCode(), element2.hashCode());
    }

    /**
     * Swap two elements in the list
     *
     * @param index1 the index of the first element
     * @param index2 the index of the second element
     */
    @SuppressWarnings("unchecked")
    private void swap(int index1, int index2) {
        E tmp = (E) arrayData[index1];
        arrayData[index1] = arrayData[index2];
        arrayData[index2] = tmp;
    }

    /**
     * Sorts the elements of the list in their hashcode order using the Bubble Sort algorithm
     */
    public void sortBubble() {
        int n = size;
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < n; i++) {
                if (compareByIndex(i, i - 1) < compareByIndex(i - 1, i)) {
                    swap(i, i - 1);
                    needIteration = true;
                }
            }
        }
    }

    public static void main(String[] args) {

        ArrayListOwn<Integer> list = new ArrayListOwn<>();

        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println("List size after adding 3 elements: " + list.size());
        System.out.println(list.toString());

        list.addByIndex(1, 25);
        System.out.println("List size after adding element " + list.getByIndex(1) + " at index 1: " + list.size());
        System.out.println(list.toString());

        int element = list.getByIndex(1);
        System.out.println("Element at index 1: " + element);

        list.removeByIndex(0);
        System.out.println(list.toString() + " after delete element at index 0");

        list.clear();
        System.out.println("List size after clearing: " + list.size());

        list.add(40);
        list.add(6);
        list.add(-21);
        list.add(199);
        System.out.println("Before use bubble sort " + list.toString());
        list.sortBubble();
        System.out.println("After sort " + list.toString());
    }
}




