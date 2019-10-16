package labs.l02_linear_ds_stacks_and_queues.exercises.p03_array_stack;

public class ArrayStack<T> {

    private static final int INITIAL_CAPACITY = 16;

    private T[] elements;
    private int size;

    public ArrayStack() {
        this(INITIAL_CAPACITY);
    }

    public ArrayStack(int capacity) {
        this.elements = (T[]) new Object[capacity];
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(T element) {
        if(this.size == this.elements.length - 1) {
            this.grow();
        }
        this.elements[this.size] = element;
        this.size++;
    }

    public T pop() {
        if(this.size <= 0) {
            throw new IllegalArgumentException();
        }
        T element = this.elements[this.size - 1];
        this.size--;
        return element;
    }

    public T[] toArray() {
        T[] result = (T[]) new Object[this.size];
        for (int i = 0; i < this.size; i++) {
            result[i] = this.elements[this.size - 1 - i];
        }
        return result;
    }

    private void grow() {
        T[] newArray = (T[]) new Object[this.elements.length * 2];
        for (int i = 0; i < this.size; i++) {
            newArray[i] = this.elements[i];
        }
        this.elements = newArray;
    }

}