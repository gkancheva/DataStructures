package labs.l01_linear_ds_list_and_arrays.p08_doubly_linked_list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Iterable<T> {
    private int size;
    private Node head;
    private Node tail;

    public int size() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void addFirst(T element) {
        Node oldHead = this.head;
        this.head = new Node(element);
        this.head.next = oldHead;
        if(this.size() == 0) {
            this.tail = head;
        } else {
            oldHead.previous = this.head;
        }
        this.size++;
    }

    public void addLast(T element) {
        Node oldTail = this.tail;
        this.tail = new Node(element);
        this.tail.previous = oldTail;
        if(this.size() == 0) {
            this.head = tail;
        } else {
            oldTail.next = this.tail;
        }
        this.size++;
    }

    public T removeFirst() {
        if(this.size() <= 0) {
            throw new IllegalArgumentException();
        }
        T element = this.head.value;
        this.head = this.head.next;
        if(this.head != null) {
            this.head.previous = null;
        } else {
            this.tail = null;
        }
        this.size--;
        return element;
    }

    public T removeLast() {
        if (this.size() <= 0) {
            throw new IllegalArgumentException();
        }
        T element = this.tail.value;
        this.tail = this.tail.previous;
        if (this.tail != null) {
            this.tail.next = null;
        } else {
            this.head = null;
        }
        this.size--;
        return element;
    }

    public T[] toArray() {
        T[] array = (T[]) new Object[this.size];
        int index = 0;
        Node currentNode = this.head;
        while (currentNode != null) {
            array[index++] = currentNode.value;
            currentNode = currentNode.next;
        }
        return array;
    }

    @Override
    public Iterator<T> iterator() {
        return new DoublyLikedListIterator();
    }

    private final class DoublyLikedListIterator implements Iterator<T> {
        private Node current;

        private DoublyLikedListIterator() {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public T next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            T element = this.current.value;
            this.current = this.current.next;
            return element;
        }
    }

    private class Node {
        private T value;
        private Node next;
        private Node previous;

        Node(T value) {
            this.value = value;
        }
    }

}