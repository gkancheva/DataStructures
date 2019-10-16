package labs.l06_b_trees_and_red_black_trees.hierarchy.test.correctness;

import labs.l06_b_trees_and_red_black_trees.hierarchy.test.helpers.BaseTest;
import labs.l06_b_trees_and_red_black_trees.hierarchy.test.types.CorrectnessTests;
import labs.l06_b_trees_and_red_black_trees.hierarchy.Hierarchy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class Constructor extends BaseTest {

    @Category(CorrectnessTests.class)
    @Test
    public void Constructor_NewHierarchyShouldHaveExactly1Element()
    {
        Hierarchy<Integer> hierarchy = new Hierarchy<>(5);
        Assert.assertEquals(1, hierarchy.getCount());
    }

    @Category(CorrectnessTests.class)
    @Test
    public void Constructor_NewHierarchyShouldHaveCorrectElement()
    {
        Hierarchy<Integer> hierarchy = new Hierarchy<>(5);
        Assert.assertTrue(hierarchy.contains(5));
    }

    @Category(CorrectnessTests.class)
    @Test
    public void Hierarchy_ShouldBeGeneric()
    {
        Hierarchy<String> hierarchy = new Hierarchy<String>("exam_prep/sep_2018_scheduler/test");
        Assert.assertTrue(hierarchy.contains("exam_prep/sep_2018_scheduler/test"));
    }
}
