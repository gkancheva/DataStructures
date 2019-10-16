package labs.l11_combining_data_structures.exercises.shopping_center;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ProductCollectionImpl implements ProductCollection {

    private TreeMap<String, List<Product>> productsByProducer;
    private TreeMap<String, List<Product>> productsByName;
    private TreeMap<BigDecimal, List<Product>> productsByPrice;

    public ProductCollectionImpl() {
        this.productsByProducer = new TreeMap<>();
        this.productsByName = new TreeMap<>();
        this.productsByPrice = new TreeMap<>();
    }

    @Override
    public boolean addProduct(String name, BigDecimal price, String producer) {
        Product product = new Product(name, price, producer);
        this.productsByProducer.putIfAbsent(producer, new LinkedList<>());
        this.productsByProducer.get(producer).add(product);

        this.productsByName.putIfAbsent(name, new LinkedList<>());
        this.productsByName.get(name).add(product);

        this.productsByPrice.putIfAbsent(price, new LinkedList<>());
        this.productsByPrice.get(price).add(product);
        return true;
    }

    @Override
    public int deleteProducts(String producer) {
        if(!this.productsByProducer.containsKey(producer)) {
            return 0;
        }
        List<Product> itemsToRemove = new ArrayList<>(this.productsByProducer.getOrDefault(producer, new LinkedList<>()));
        for (Product product : itemsToRemove) {
            this.deleteProducts(product.getName(), producer);
        }
        return itemsToRemove.size();
    }

    @Override
    public int deleteProducts(String name, String producer) {
        List<Product> products = new ArrayList<>(this.productsByName.getOrDefault(name, new LinkedList<>()));
        int count = 0;
        for (Product product : products) {
            if(product.getProducer().equals(producer)) {
                this.productsByPrice.get(product.getPrice()).remove(product);
                if(this.productsByPrice.get(product.getPrice()).size() == 0) {
                    this.productsByPrice.remove(product.getPrice());
                }
                this.productsByName.get(product.getName()).remove(product);
                if(this.productsByName.get(product.getName()).size() == 0) {
                    this.productsByName.remove(product.getName());
                }
                this.productsByProducer.get(producer).remove(product);
                if(this.productsByProducer.get(product.getProducer()).size() == 0) {
                    this.productsByProducer.remove(product.getProducer());
                }
                count++;
            }
        }
        return count;
    }

    @Override
    public List<Product> findProductsByName(String name) {
        return this.productsByName.getOrDefault(name, new LinkedList<>())
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findProductsByProducer(String producer) {
        return this.productsByProducer.getOrDefault(producer, new LinkedList<>())
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findProductsByPriceRange(BigDecimal from, BigDecimal to) {
        LinkedList<Product> result = new LinkedList<>();
        SortedMap<BigDecimal, List<Product>> sortedList =
                this.productsByPrice.subMap(from, to.add(new BigDecimal(0.00000000000000000001)));
        for (List<Product> products : sortedList.values()) {
            result.addAll(products);
        }
        return result
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }
}