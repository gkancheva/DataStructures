package labs.l02_linear_ds_stacks_and_queues.lab2_circular_queue;

import java.util.Iterator;

public class CircularQueue<E> implements Iterable {

    private static int DEFAULT_CAPACITY = 4;
    private int size;
    private E[] elements;
    private int startIndex;
    private int endIndex;

    public CircularQueue() {
        this(DEFAULT_CAPACITY);
    }

    public CircularQueue(int initialCapacity) {
        this.elements = (E[])new Object[initialCapacity];
    }

    public int size() {
        return this.size;
    }

    private  void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        if(this.size >= this.elements.length) {
            this.resize();
        }
        this.elements[this.endIndex] = element;
        this.endIndex = (this.endIndex + 1) % this.elements.length;
        this.size++;
    }

    private void resize() {
        E[] newArray = (E[]) new Object[this.elements.length * 2];
        this.copyElements(newArray);
        this.elements = newArray;
        this.startIndex = 0;
        this.endIndex = this.size;
    }

    private void copyElements(E[] newArray) {
        for (int i = 0; i < this.size; i++) {
            int index = (i + this.startIndex) % this.elements.length;
            newArray[i] = this.elements[index];
        }
        this.startIndex = 0;
        this.endIndex = this.size;
    }

    public E dequeue() {
        if(this.size == 0) {
            throw new IllegalArgumentException();
        }
        E result = this.elements[this.startIndex];
        startIndex = (this.startIndex + 1) % this.elements.length;
        this.size--;
        return result;
    }

    public E[] toArray() {
        E[] newArray = (E[]) new Object[this.size];
        this.copyElements(newArray);
        return newArray;
    }

    @Override
    public Iterator<E> iterator() {
        return new CircularQueueIterator();
    }

    private class CircularQueueIterator implements Iterator<E> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }
    }

}
