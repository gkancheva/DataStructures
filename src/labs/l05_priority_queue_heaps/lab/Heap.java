package labs.l05_priority_queue_heaps.lab;

public class Heap {

    public static <E extends Comparable<E>> void sort(E[] array) {
        if(array.length > 1) {
            for (int i = array.length / 2; i >= 0 ; i--) {
                heapifyDown(array, i, array.length);
            }
            for (int i = array.length - 1; i > 0 ; i--) {
                swap(0, i, array);
                heapifyDown(array, 0, i);
            }
        }
    }

    private static <E extends Comparable<E>> void heapifyDown(E[] arr, int i, int length) {
        while (i < length / 2) {
            E element = arr[i];
            int childIndex = i * 2 + 1;
            if (length > i * 2 +2 && arr[i * 2 +2].compareTo(arr[childIndex]) > 0) {
                childIndex++;
            }
            if (element.compareTo(arr[childIndex]) < 0) {
                swap(i, childIndex, arr);
                i = childIndex;
            } else {
                break;
            }
        }
    }

    private static <E> void swap(int parentIndex, int index, E[] arr) {
        E temp = arr[parentIndex];
        arr[parentIndex] = arr[index];
        arr[index] = temp;

    }
}