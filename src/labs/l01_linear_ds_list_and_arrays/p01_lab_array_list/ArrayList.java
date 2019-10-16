package labs.l01_linear_ds_list_and_arrays.p01_lab_array_list;

public class ArrayList<T> {
    private static int INITIAL_CAPACITY = 2;
    private T[] items;
    private int count;

    public ArrayList() {
        this.items = (T[]) new Object[INITIAL_CAPACITY];
        this.count = 0;
    }

    public int getCount() {
        return this.count;
    }

    public T get(int index) {
        if(index >= items.length - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return items[index];
    }

    public void set(T element, int index) {
         if(index < 0 || index >= items.length) {
             throw new IndexOutOfBoundsException();
         }
         this.items[index] = element;
    }

    public void add(T element) {
        if(this.count >= this.items.length){
            this.resize();
        }
        this.items[this.count++] = element;
    }

    public T removeAt(int index) {
        if(index < 0 || index >= this.count) {
            throw new IndexOutOfBoundsException();
        }
        T element = this.items[index];
        this.items[index] = null;
        this.shift(index);
        this.count--;
        if(this.count <= this.items.length / 4) {
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

    private void shift(int index) {
        for (int i = 0; i < this.count; i++) {
            this.items[i] = this.items[i + 1];
        }
    }


    private void resize() {
        T[] copy = (T[]) new Object[this.items.length * 2];
        for (int i = 0; i < this.items.length; i++) {
            copy[i] = this.items[i];
        }
        this.items = copy;
    }

}