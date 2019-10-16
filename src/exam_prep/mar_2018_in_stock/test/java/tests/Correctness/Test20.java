package exam_prep.mar_2018_in_stock.test.java.tests.Correctness;

import exam_prep.mar_2018_in_stock.instock.Instock;
import exam_prep.mar_2018_in_stock.instock.Product;
import exam_prep.mar_2018_in_stock.instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

public class Test20 {

    @Test(expected = IllegalArgumentException.class)
    public void findFirstMostExpensiveProducts_On_WrongArgumentPassed_ShouldThrow() {
        //Arrange
        ProductStock stock = new Instock();
        Product product1 = new Product("SalamShpekov", 3.50, 50);
        Product product2 = new Product("BekonNov", 2.65, 43);
        Product product3 = new Product("MayonezaNiskomaslena", 1.30, 13);
        //Act
        stock.add(product1);
        stock.add(product2);
        stock.add(product3);
        //Assert
        Assert.assertEquals(3, stock.getCount());
        stock.findFirstMostExpensiveProducts(5).iterator().next();
    }

}
