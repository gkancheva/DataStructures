package labs.l02_linear_ds_stacks_and_queues.lab1_linked_list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Iterable<E> {

    private int size;
    private Node head;
    private Node tail;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void addFirst(E item) {
        Node oldHead = this.head;
        this.head = new Node(item);
        this.head.next = oldHead;
        if(this.size == 0) {
            this.tail = this.head;
        }
        size++;
    }

    public void addLast(E item) {
        Node oldTail = this.tail;
        this.tail = new Node(item);
        if(this.size == 0) {
            this.head = this.tail;
        } else {
            oldTail.next = this.tail;
        }
        this.size++;
    }

    public E removeFirst() {
        if(this.size == 0) {
            throw new UnsupportedOperationException();
        }
        Node oldHead = this.head;
        this.head = this.head.next;
        this.size--;
        if(this.size == 0) {
            this.tail = null;
        }
        return (E) oldHead.value;
    }

    public E removeLast() {
        if(this.size == 0) {
            throw new UnsupportedOperationException();
        }
        Node oldTail = this.tail;
        if(this.size == 1) {
            this.head = this.tail;
            this.tail = null;
        } else {
            Node secondToLastNode = this.secondToLast();
            secondToLastNode.next = null;
            this.tail = secondToLastNode;
        }
        this.size--;
        return (E) oldTail.value;
    }

    private Node secondToLast() {
        Node current = this.head;
        while(current.next != this.tail) {
            current = current.next;
        }
        return current;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E> {
        private Node current;

        private LinkedListIterator() {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return this.current == tail;
        }

        @Override
        public E next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = (E) this.current.value;
            this.current = this.current.next;
            return element;
        }

    }

    private class Node<T> {
        private T value;
        private Node next;

        public Node(T value) {
            this.value = value;
        }

        public Node getNext() {
            return this.next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

}
