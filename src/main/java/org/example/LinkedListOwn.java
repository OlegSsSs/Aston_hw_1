package org.example;

import java.util.Comparator;

/**
 * LinkedList - own implementation LinkedList.
 * Methods include:
 * adding an element
 * adding an element by index
 * getting an element by index
 * deletion element by value and by index
 * clearing the collection
 * sort list
 *
 * @param <T> the type of elements stored in the list
 * @author OlegS
 */
public class LinkedListOwn<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Node class representing an element in the linked list.
     *
     * @param <T> the type of data stored in the node
     */
    private static class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node(T element) {
            data = element;
            next = null;
            prev = null;
        }
    }

    /**
     * Retrieves the node at the specified index.
     *
     * @param index the index of the node to retrieve
     * @return the node at the specified index
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    private Node<T> getNodeByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> currNode = head;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }
        return currNode;
    }

    /**
     * Retrieves the element at the specified index.
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public T getElementByIndex(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return getNodeByIndex(index).data;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element the element to be added
     */
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * Adds an element at the specified index.
     *
     * @param index   the index at which the element should be added
     * @param element the element to be added
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public void addByIndex(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            add(element);
        } else {
            Node<T> newNode = new Node<>(element);
            if (index == 0) {
                newNode.next = head;
                if (head != null) {
                    head.prev = newNode;
                }
                head = newNode;
            } else {
                Node<T> prevNode = getNodeByIndex(index - 1);

                newNode.next = prevNode.next;
                newNode.prev = prevNode;
                prevNode.next = newNode;

                if (newNode.next != null) {
                    newNode.next.prev = newNode;
                } else {
                    tail = newNode;
                }
            }
            size++;
        }
    }

    /**
     * Returns a string representation of the list.
     *
     * @return a string representation of the list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node<T> currNode = head;
        while (currNode != null) {
            sb.append(currNode.data);
            if (currNode.next != null) {
                sb.append(" ");
            }
            currNode = currNode.next;
        }
        return sb.toString();
    }

    /**
     * Remove the specified node from the linked list.
     *
     * @param node the node to be deleted.
     */
    private void removeNode(Node<T> node) {
        if (head == node) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
        } else if (tail == node) {
            tail = tail.prev;
            tail.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        --size;
    }

    /**
     * Removes the node at the specified index.
     *
     * @param index the index of the node to be removed
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public void removeByIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> currNode = getNodeByIndex(index);
        removeNode(currNode);
    }

    /**
     * Clears the entire list.
     */
    public void clear() {
        if (head != null) {
            while (head.next != null) {
                head.next = null;
            }
            head = null;
        }
    }

    /**
     * Sorts the list using the provided comparator.
     *
     * @param comparator the comparator to be used for sorting
     */
    public void sortList(Comparator<T> comparator) {
        if (size > 1) {
            for (Node<T> i = head; i != null; i = i.next) {
                for (Node<T> j = i.next; j != null; j = j.next) {
                    if (comparator.compare(i.data, j.data) > 0) {
                        T tmp = i.data;
                        i.data = j.data;
                        j.data = tmp;
                    }
                }
            }
        }
    }


    public static void main(String[] args) {

        LinkedListOwn<Integer> list = new LinkedListOwn<>();

        list.add(10);
        list.add(123);
        list.add(45);
        list.add(-12);
        list.addByIndex(0, 20);
        System.out.println(list.getElementByIndex(0));
        System.out.println(list.toString());

        list.removeByIndex(2);
        System.out.println(list.toString());

        list.clear();
        System.out.println(list.toString());

        list.add(1);
        list.add(123);
        list.add(0);
        list.add(-12);
        list.add(25);
        list.add(-123);
        System.out.println(list.toString());

        list.sortList(Comparator.reverseOrder());
        System.out.println(list.toString());


    }
}


