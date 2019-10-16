package labs.l01_linear_ds_list_and_arrays.p08_doubly_linked_list;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        list.forEach(System.out::println);
        System.out.println();

        list.addLast(5);
        list.addFirst(3);
        list.addFirst(2);
        list.addLast(10);

        System.out.println(Arrays.toString(list.toArray()));


    }
}