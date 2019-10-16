package labs.l06_b_trees_and_red_black_trees.hierarchy.test.helpers;

import labs.l06_b_trees_and_red_black_trees.hierarchy.IHierarchy;
import labs.l06_b_trees_and_red_black_trees.hierarchy.test.HierarchyStructureInitializer;
import org.junit.Before;

public class BaseTest {

    protected static final int DefaultRootValue = 5;
    protected IHierarchy<Integer> Hierarchy;

    @Before
    public void setUp() {
        this.Hierarchy = HierarchyStructureInitializer.create(DefaultRootValue);
    }
}
