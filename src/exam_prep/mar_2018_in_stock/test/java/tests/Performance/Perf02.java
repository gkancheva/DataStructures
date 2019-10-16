package exam_prep.mar_2018_in_stock.test.java.tests.Performance;

import exam_prep.mar_2018_in_stock.instock.Instock;
import exam_prep.mar_2018_in_stock.instock.Product;
import exam_prep.mar_2018_in_stock.instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class Perf02 {
    @Test
    public void contains_100000_Elements_ShouldExecuteFast()
    {
        // Arrange
        ProductStock stock = new Instock();
        int count = 100000;
        LinkedList<Product> products = new LinkedList<Product>();

        for (int i = 0; i < count; i++)
        {
            products.addLast(new Product(String.valueOf(i), i, i));
            stock.add(products.getLast());
        }

        // Act
        long start = System.currentTimeMillis();

        for(Product p : products)
        {
            Assert.assertTrue(stock.contains(p));
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 250);
    }
}
