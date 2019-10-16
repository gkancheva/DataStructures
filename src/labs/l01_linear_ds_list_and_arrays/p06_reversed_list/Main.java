package labs.l01_linear_ds_list_and_arrays.p06_reversed_list;

public class Main {
    public static void main(String[] args) {
        ReversedList<Integer> list = new ReversedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.count());
        System.out.println(list.capacity());

        System.out.println(list.removeAt(0));
        System.out.println(list.count());
        System.out.println(list.capacity());

        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
