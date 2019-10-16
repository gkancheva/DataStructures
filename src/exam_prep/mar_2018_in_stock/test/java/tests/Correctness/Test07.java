package exam_prep.mar_2018_in_stock.test.java.tests.Correctness;

import exam_prep.mar_2018_in_stock.instock.Instock;
import exam_prep.mar_2018_in_stock.instock.Product;
import exam_prep.mar_2018_in_stock.instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

public class Test07 {

    //FindProductByIndex (InsertionOrder)
    @Test(expected = IndexOutOfBoundsException.class)
    public void find_Product_WrongIndex_ShouldThrow() {
        //Arrange
        ProductStock stock = new Instock();
        Product product = new Product("Salam", 2.50, 50);
        //Act
        stock.add(product);
        //Assert
        boolean throwed = false;
        try{
            stock.find(-5);
        }
        catch(IndexOutOfBoundsException ex){
            throwed = true;
        }
        Assert.assertTrue(throwed);

        stock.find(1);
    }
}
