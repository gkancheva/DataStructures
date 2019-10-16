package labs.l04_binary_search_tree.p01_binary_search_tree;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(1);
        bst.insert(3);
        bst.insert(4);
        bst.insert(5);
        bst.insert(8);
        bst.insert(9);
        bst.insert(10);
        bst.insert(37);
        bst.insert(39);
        bst.insert(45);
        bst.eachInOrder(a -> System.out.print(a + " "));
        System.out.println();
//        bst.deleteMax();
//        bst.eachInOrder(a -> System.out.print(a + " "));
//        System.out.println();
//        bst.deleteMin();
//        bst.eachInOrder(a -> System.out.print(a + " "));

//        int rank = bst.select(4);
//        System.out.println(rank);

//        int floorElement = bst.floor(14);
//        System.out.println(floorElement);

//        int ceilElement = bst.ceil(14);
//        System.out.println(ceilElement);

        bst.delete(3);
        bst.eachInOrder(a -> System.out.print(a + " "));
    }
}