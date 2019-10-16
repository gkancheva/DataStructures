package labs.l06_b_trees_and_red_black_trees.hierarchy.test;

import labs.l06_b_trees_and_red_black_trees.hierarchy.Hierarchy;
import labs.l06_b_trees_and_red_black_trees.hierarchy.IHierarchy;

public class HierarchyStructureInitializer {
    
    public static <T> IHierarchy<T> create(T root) {
        return new Hierarchy<>(root);
    }
}
