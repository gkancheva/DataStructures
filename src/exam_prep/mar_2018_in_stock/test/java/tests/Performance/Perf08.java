package exam_prep.mar_2018_in_stock.test.java.tests.Performance;

import exam_prep.mar_2018_in_stock.instock.Instock;
import exam_prep.mar_2018_in_stock.instock.Product;
import exam_prep.mar_2018_in_stock.instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Perf08 {
    @Test
    public void productByInsertOrder_ShouldWorkFast()
    {
        // Arrange
        ProductStock stock = new Instock();
        int count = 100000;
        List<Product> people = new ArrayList<>();

        for (int i = 0; i < count; i++)
        {
            people.add(new Product(String.valueOf(i), i, i));
            stock.add(people.get(i));
        }

        long start = System.currentTimeMillis();
        // Act & Assert
        int c = 0;
        for(Product p : stock){
            Assert.assertSame(p, people.get(c));
            c++;
        }

        long end = System.currentTimeMillis();
        Assert.assertEquals(c, people.size());
        Assert.assertTrue(end - start < 150);
    }
}
