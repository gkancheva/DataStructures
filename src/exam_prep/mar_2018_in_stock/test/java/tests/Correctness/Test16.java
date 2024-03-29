package exam_prep.mar_2018_in_stock.test.java.tests.Correctness;

import exam_prep.mar_2018_in_stock.instock.Instock;
import exam_prep.mar_2018_in_stock.instock.Product;
import exam_prep.mar_2018_in_stock.instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test16 {
    @Test
    public void findFirstByAlphabeticalOrder_On_EmptyStock_ShouldReturn_EmptyEnumeration() {
        //Arrange
        ProductStock stock = new Instock();
        //Act
        //Assert
        List<Product> result = new ArrayList<>();
        Iterable<Product> res = stock.findFirstByAlphabeticalOrder(0);
        for(Product p : res){
            result.add(p);
        }
        Product[] actual = new Product[result.size()];
        for(int i = 0 ; i < result.size(); i++){
            actual[i] = result.get(i);
        }
        Assert.assertArrayEquals(new Product[0], actual);
    }
}
