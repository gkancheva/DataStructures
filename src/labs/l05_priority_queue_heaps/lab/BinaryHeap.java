package labs.l05_priority_queue_heaps.lab;

import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

    private List<T> heap;

    public BinaryHeap() {
        this.heap = new ArrayList<>();
    }

    public int size() {
        return this.heap.size();
    }

    public void insert(T element) {
        this.heap.add(element);
        this.heapifyUp(element, this.heap.size() - 1);
    }

    public T peek() {
        return this.heap.get(0);
    }

    public T pull() {
        if(this.heap.size() == 0) {
            throw new IllegalArgumentException();
        }
        T element = this.heap.get(0);
        this.swap(0, this.size() - 1);
        this.heap.remove(this.heap.get(this.size() - 1));
        this.heapifyDown();
        return element;
    }

    public void decreaseKey(T element) {
        int index = this.heap.indexOf(element);
        heapifyUp(element, index);
    }

    //Max heap (max element is the priority)
    private void heapifyUp(T element, int index) {
        int parentIndex = (index - 1) / 2;
        if(parentIndex < 0) {
            return;
        }
        T parent = this.heap.get(parentIndex);
        int compare = parent.compareTo(element);
        if(compare < 0) { // parent < element
            this.swap(parentIndex, index);
            this.heapifyUp(this.heap.get(parentIndex), parentIndex);
        }
    }

    private void swap(int parentIndex, int index) {
        T temp = this.heap.get(parentIndex);
        this.heap.set(parentIndex, this.heap.get(index));
        this.heap.set(index, temp);

    }

    private void heapifyDown() {
        int i = 0;
        int leftChildIndex = (i * 2) + 1;
        int rightChildIndex = (i * 2) + 2;
        while (i < this.size() / 2) {
            T element = this.heap.get(i);
            if (this.size() > i * 2 + 2 && this.heap.get(rightChildIndex).compareTo(this.heap.get(leftChildIndex)) > 0) {
                leftChildIndex++;
            }
            if (element.compareTo(this.heap.get(leftChildIndex)) < 0) {
                swap(i, leftChildIndex);
                i = leftChildIndex;
            } else {
                break;
            }
        }
    }

}
