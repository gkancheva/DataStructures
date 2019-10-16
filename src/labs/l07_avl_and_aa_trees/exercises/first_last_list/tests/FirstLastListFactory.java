package labs.l07_avl_and_aa_trees.exercises.first_last_list.tests;

import labs.l07_avl_and_aa_trees.exercises.first_last_list.FirstLastList;
import labs.l07_avl_and_aa_trees.exercises.first_last_list.IFirstLastList;

public class FirstLastListFactory {
    public static <T extends Comparable<T>> IFirstLastList<T> create() {
    	return new FirstLastList<T>();
    }
}
