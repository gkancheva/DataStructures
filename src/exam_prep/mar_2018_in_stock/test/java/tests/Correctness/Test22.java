package exam_prep.mar_2018_in_stock.test.java.tests.Correctness;

import exam_prep.mar_2018_in_stock.instock.Instock;
import exam_prep.mar_2018_in_stock.instock.Product;
import exam_prep.mar_2018_in_stock.instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test22 {
    //FindByQuantity
    @Test
    public void findAllByQuantity_Should_Return_Correct_Enumeration() {
        //Arrange
        ProductStock stock = new Instock();
        Product product1 = new Product("SalamShpekov", 3.50, 50);
        Product product2 = new Product("BekonNov", 2.65, 43);
        Product product3 = new Product("MayonezaNiskomaslena", 1.30, 13);
        Product product4 = new Product("Ketchup", 1.80, 73);
        Product product5 = new Product("Jelqzo", 0.70, 20);
        Product product6 = new Product("Belina", .75, 50);
        Product product7 = new Product("Sirene", .77, 50);
        //Act
        stock.add(product1);
        stock.add(product2);
        stock.add(product3);
        stock.add(product4);
        stock.add(product5);
        stock.add(product6);
        stock.add(product7);
        //Assert
        Product[] expected = new Product[] {
                product1,product6,product7
        };
        List<Product> result = new ArrayList<>();
        Iterable<Product> res = stock.findAllByQuantity(50);
        for(Product p : res){
            result.add(p);
        }
        Product[] actual = new Product[result.size()];
        for(int i = 0 ; i < result.size(); i++){
            actual[i] = result.get(i);
        }
        Assert.assertArrayEquals(expected, actual);
    }

}
