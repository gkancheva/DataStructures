package labs.l01_linear_ds_list_and_arrays.p06_reversed_list;

import java.util.Iterator;

public class ReversedList<T> implements Iterable<T> {
    private T[] items;
    private int count;
    private int capacity = 2;

    public ReversedList() {
        this.count = 0;
        this.items = (T[])new Object[capacity];
    }

    public int count() {
        return this.count;
    }

    public int capacity() {
        return this.capacity;
    }

    public void add(T item) {
        if(this.count >= this.items.length) {
            this.grow();
        }
        this.items[this.count++] = item;
    }

    public T get(int index) {
        return this.items[this.count - 1 - index];
    }

    public void set(int index, T element) {
        this.items[this.count - 1 - index] = element;
    }

    public T removeAt(int index) {
        T element = this.get(index);
        this.shiftLeft(this.count - 1 - index);
        if(this.count-- < this.capacity / 4) {
            this.shrink();
        }
        return element;
    }

    private void shrink() {
        T[] copy = (T[]) new Object[this.items.length / 2];
        for (int i = 0; i < this.count; i++) {
            copy[i] = this.items[i];
        }
        this.items = copy;
    }

    private void shiftLeft(int index) {
        for (int i = index; i < this.count - 1; i++) {
            this.items[i] = this.items[i + 1];
        }
        this.items[this.count - 1] = null;
    }

    private void grow() {
        T[] copy = (T[]) new Object[this.items.length * 2];
        for (int i = 0; i < this.items.length; i++) {
            copy[i] = this.items[i];
        }
        this.items = copy;
        this.capacity = this.items.length;
    }

    public Iterator<T> iterator() {
        return new ReverseListIterator<T>();
    }

    public class ReverseListIterator <T> implements Iterator<T> {
        private int current = count - 1;

        public boolean hasNext() {
            return current >= 0;
        }

        public T next() {
            if(hasNext()) {
                return (T)items[current--];
            }
            return null;
        }
    }
}