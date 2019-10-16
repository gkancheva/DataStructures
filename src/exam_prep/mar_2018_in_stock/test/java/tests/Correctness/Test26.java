package exam_prep.mar_2018_in_stock.test.java.tests.Correctness;

import exam_prep.mar_2018_in_stock.instock.Instock;
import exam_prep.mar_2018_in_stock.instock.Product;
import exam_prep.mar_2018_in_stock.instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

public class Test26 {
    @Test
    public void enumerator_ShouldReturn_EmptyEnumeration_On_Empty_Stock() {
        //Arrange
        ProductStock stock = new Instock();
        //Act
        //Assert
        Product[] expected = new Product[0];
        Product[] actual = new Product[stock.getCount()];
        int i = 0 ;
        for(Product product : stock){
            actual[i] = product;
            i++;
        }
        Assert.assertArrayEquals(expected, actual);
    }
}
