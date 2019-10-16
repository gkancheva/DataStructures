package labs.l01_linear_ds_list_and_arrays.p01_lab_array_list;

public class Main {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(5);
        list.set((int)list.get(0) + 1, 0);
        int element = (int)list.removeAt(0);
        System.out.println(element);
    }
}