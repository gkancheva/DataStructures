package exam_prep.mar_2018_in_stock.test.java.tests.Performance;

import exam_prep.mar_2018_in_stock.instock.Instock;
import exam_prep.mar_2018_in_stock.instock.Product;
import exam_prep.mar_2018_in_stock.instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

public class Perf13 {
    @Test
    public void FindAllInPriceRange_OnLargeRange_ShouldWorkFast() {
        // Arrange
        ProductStock stock = new Instock();
        int count = 100000;
        int expected = 0;

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int price = random.nextInt(50000);
            if (price > 105 && price <= 10000) expected++;

            stock.add(new Product(String.valueOf(i), price, i));
        }

        long start = System.currentTimeMillis();
        // Act
        // Assert
        Assert.assertEquals(expected, ((ArrayList) stock.findAllInRange(105, 10000)).size());

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 300);
    }
}
