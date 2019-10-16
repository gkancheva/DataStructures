package labs.l11_combining_data_structures.exercises.shopping_center;

import java.math.BigDecimal;
import java.util.List;

public interface ProductCollection {

    boolean addProduct(String name, BigDecimal price, String producer);
    int deleteProducts(String producer);
    int deleteProducts(String name, String producer);
    List<Product> findProductsByName(String name);
    List<Product> findProductsByProducer(String producer);
    List<Product> findProductsByPriceRange(BigDecimal fromPrice, BigDecimal toPrice);
}