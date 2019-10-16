package exam_prep.mar_2018_in_stock.test.java.tests.Correctness;

import exam_prep.mar_2018_in_stock.instock.Instock;
import exam_prep.mar_2018_in_stock.instock.Product;
import exam_prep.mar_2018_in_stock.instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

public class Test06 {
    @Test
    public void contains_On_Empty_Collection_ShouldReturnFalse() {
        //Arrange
        ProductStock stock = new Instock();
        //Act
        //Assert
        Assert.assertFalse(stock.contains(new Product("Bakar", 5.5, 15)));
    }
}
