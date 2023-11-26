package org.example;

/**
 * LinkedList - own implementation LinkedList.
 * Methods include:
 * adding an element
 * adding an element by index
 * getting an element by index
 * remove element by key and by index
 * clearing the collection
 * sort insertion
 *
 * @author OlegS
 *
 */

public class LinkedListOwn {

    /**
     * Head of list
     */
    Node head;

    /**
     *  Linked list Node. Node is a static nested class
     */
    static class Node {
        int data;
        Node next;

        public Node(int d) {
            data = d;
            next = null;
        }
    }

    /**
     * Method to insert a new node
     *
     * @param list to which is added data
     * @param data the int data to be added
     */
    public static void add(LinkedListOwn list, int data) {

        Node newNode = new Node(data);
        newNode.next = null;

        if (list.head == null) {
            list.head = newNode;
        } else {
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }
    }

    /**
     * Adds a data for a specific index
     * @param list to which is added data
     * @param index index at which to add the data
     * @param data the int data to be added
     */
    public static void addByIndex(LinkedListOwn list, int index, int data) {

        Node newNode = new Node(data);

        if (index == 0) {
            newNode.next = list.head;
            list.head = newNode;
        } else {
            Node currNode = list.head;
            int currentIndex = 0;
            while (currNode != null && currentIndex < index - 1) {
                currNode = currNode.next;
                currentIndex++;
            }
            if (currNode == null) {
                System.out.println("is out of bounds for the list");
            } else {
                newNode.next = currNode.next;
                currNode.next = newNode;
            }
        }
    }

    /**
     * Returns the data at the specified index
     * @param list to which is added data
     * @param index index at which to add the data
     * @return returns the data at this index
     * @throws IndexOutOfBoundsException if the specified index is outside the valid range
     */
    public static int getElementAtIndex(LinkedListOwn list, int index) {

        Node currNode = list.head;
        int currIndex = 0;

        while (currNode != null) {
            if (currIndex == index) {
                return currNode.data;
            }
            currNode = currNode.next;
            currIndex++;
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     * Displays a list
     * @param list will be displayed on screen
     */
    public static void printList(LinkedListOwn list) {
        Node currNode = list.head;
        System.out.print("LinkedList: ");
        while (currNode != null) {
            System.out.print(currNode.data + " ");
            currNode = currNode.next;
        }
        System.out.println();
    }

    /**
     * Remove data at the value
     * @param list to which is remove data
     * @param value data to remove
     */
    public static void removeByValue(LinkedListOwn list, int value) {

        Node currNode = list.head, prev = null;

        if (currNode != null && currNode.data == value) {
            list.head = currNode.next;
            System.out.println(value + " value find and remove");
            return;
        }
        while (currNode != null && currNode.data != value) {
            prev = currNode;
            currNode = currNode.next;
        }
        if (currNode != null) {
            prev.next = currNode.next;
            System.out.println(value + " value not found");
        }
        if (currNode == null) {
            System.out.println(value + " value not found");
        }
    }

    /**
     * Remove data at the specified index
     * @param list to which is remove data
     * @param index index at which to remove the data
     */
    public static void removeByIndex(LinkedListOwn list, int index) {
        Node currNode = list.head, prev = null;

        if (index == 0 && currNode != null) {
            list.head = currNode.next;
            System.out.println("data at index: " + index + " remove");
            return;
        }
        int counter = 0;
        while (currNode != null) {
            if (counter == index) {
                prev.next = currNode.next;
                System.out.println("data at index: " + index + " remove");
                break;
            } else {
                prev = currNode;
                currNode = currNode.next;
                counter++;
            }
        }
        if (currNode == null) {
            System.out.println(index + " index not found");
        }
    }

    /**
     * Removes all data from a linked list
     * @param list to which is need remove all data
     */
    public static void clear(LinkedListOwn list) {
        list.head = null;
    }

    /**
     * Sorts a linked list in ascending order using the insertion method
     * @param list to which is need sorting
     */
    public static void sortList(LinkedListOwn list) {
        Node currNode = list.head, prev = null;
        int tmp;

        if (list.head == null) {
            System.out.println("List is empty");
        } else {
            while (currNode != null) {
                prev = currNode.next;
                while (prev != null) {
                    if (currNode.data > prev.data) {
                        tmp = currNode.data;
                        currNode.data = prev.data;
                        prev.data = tmp;
                    }
                    prev = prev.next;
                }
                currNode = currNode.next;
            }
        }
    }

    public static void main(String[] args) {

        LinkedListOwn list = new LinkedListOwn();

        add(list, 1);
        add(list, 2);
        add(list, 3);
        add(list, 4);
        add(list, 5);
        printList(list);
        addByIndex(list, 2, 23);
        printList(list);

        System.out.println(getElementAtIndex(list, 2));

        sortList(list);
        printList(list);
        clear(list);

        LinkedListOwn list2 = new LinkedListOwn();

        add(list2, 4);
        add(list2, 2);
        add(list2, 1);
        add(list2, 3);
        add(list2, 10);
        printList(list2);

        removeByValue(list2, 4);
        printList(list2);

        LinkedListOwn list1 = new LinkedListOwn();

        add(list1, 1);
        add(list1, 2);
        add(list1, 3);
        add(list1, 4);
        add(list1, 5);
        add(list1, 6);
        add(list1, 7);
        add(list1, 8);
        printList(list1);

        removeByIndex(list1, 0);
        printList(list1);

        removeByIndex(list1, 2);
        printList(list1);

        removeByIndex(list1, 10);
        printList(list1);
    }
}


