package labs.l06_b_trees_and_red_black_trees.hierarchy.test.correctness;

import labs.l06_b_trees_and_red_black_trees.hierarchy.Hierarchy;
import labs.l06_b_trees_and_red_black_trees.hierarchy.test.helpers.BaseTest;
import labs.l06_b_trees_and_red_black_trees.hierarchy.test.helpers.IterableExtensions;
import labs.l06_b_trees_and_red_black_trees.hierarchy.test.types.CorrectnessTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Arrays;
import java.util.List;

public class GetCommonElements extends BaseTest {

    @Category(CorrectnessTests.class)
    @Test
    public void GetCommonElements_WithHierarchyWithoutCommonElements_ShouldReturnAnEmptyCollection()
    {
        Hierarchy<Integer> otherHierarchy = new Hierarchy<>(1);

        Iterable<Integer> commonElements = this.Hierarchy.GetCommonElements(otherHierarchy);

        Assert.assertEquals(0, IterableExtensions.getCount(commonElements));
    }

    @Category(CorrectnessTests.class)
    @Test
    public void GetCommonElements_WithHierarchyWithOneCommonElement_ShouldReturnACollectionOfCorrectElement()
    {
        Hierarchy<Integer> otherHierarchy = new Hierarchy<>(1);
        otherHierarchy.add(1, 13);
        this.Hierarchy.Add(DefaultRootValue, 13);

        Iterable<Integer> result = this.Hierarchy.GetCommonElements(otherHierarchy);

        Assert.assertEquals(1, IterableExtensions.getCount(result));
        Assert.assertEquals(13, (int) result.iterator().next());
    }

    @Category(CorrectnessTests.class)
    @Test
    public void GetCommonElements_WithHierarchyWithMultipleCommonElements_ShouldReturnACorrectCollection()
    {
        Hierarchy<Integer> otherHierarchy = new Hierarchy<>(10);
        otherHierarchy.add(10, -22);
        otherHierarchy.add(-22, 56);
        otherHierarchy.add(10, 108);
        otherHierarchy.add(-22, 34);
        this.Hierarchy.Add(DefaultRootValue, 100);
        this.Hierarchy.Add(DefaultRootValue, -22);
        this.Hierarchy.Add(100, 34);
        this.Hierarchy.Add(100, 10);

        Iterable<Integer> commonElements = this.Hierarchy.GetCommonElements(otherHierarchy);
        List<Integer> result = IterableExtensions.toList(commonElements);

        Assert.assertTrue(result.equals(Arrays.asList(-22, 34, 10)));
    }
}
