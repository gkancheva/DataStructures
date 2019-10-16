package exam_prep.mar_2018_in_stock.instock;

import java.util.*;
import java.util.stream.Collectors;

public class Instock implements ProductStock {

    private List<Product> products;
    private Map<String, Product> byLabel;
    private TreeMap<Double, List<Product>> byPrice;
    private Map<Integer, List<Product>> byQuantity;

    public Instock() {
        this.products = new ArrayList<>();
        this.byLabel = new TreeMap<>();
        this.byPrice = new TreeMap<>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return Double.compare(o2, o1);
            }
        });
        this.byQuantity = new HashMap<>();
    }

    @Override
    public int getCount() {
        return this.products.size();
    }

    @Override
    public boolean contains(Product product) {
        return this.byLabel.containsKey(product.getLabel());
    }

    @Override
    public void add(Product product) {
        this.products.add(product);
        this.byLabel.put(product.getLabel(), product);
        this.byPrice.putIfAbsent(product.getPrice(), new LinkedList<>());
        this.byPrice.get(product.getPrice()).add(product);
        this.byQuantity.putIfAbsent(product.getQuantity(), new LinkedList<>());
        this.byQuantity.get(product.getQuantity()).add(product);
    }

    @Override
    public void changeQuantity(String label, int quantity) {
        if(!this.byLabel.containsKey(label)) {
            throw new IllegalArgumentException();
        }
        Product product = this.byLabel.get(label);
        this.byQuantity.get(product.getQuantity()).remove(product);
        product.setQuantity(quantity);
        this.byQuantity.putIfAbsent(product.getQuantity(), new LinkedList<>());
        this.byQuantity.get(product.getQuantity()).add(product);
        this.byLabel.get(label).setQuantity(quantity);
    }

    @Override
    public Product find(int index) {
        if(index < 0 || index >= this.products.size()) {
            throw new IndexOutOfBoundsException();
        }
        return this.products.get(index);
    }

    @Override
    public Product findByLabel(String label) {
        if(!this.byLabel.containsKey(label)) {
            throw new IllegalArgumentException();
        }
        return this.byLabel.get(label);
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        List<Product> result = this.byLabel.values()
                .stream()
                .limit(count).collect(Collectors.toList());
        if(result == null || result.size() < count) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        //NavigableMap<Double, List<Product>> map = this.byPrice.subMap(lo, false, hi, true);
        List<Double> keys = this.byPrice.keySet().stream()
                .filter(x -> x > lo && x <= hi).collect(Collectors.toList());
        List<Product> result = new ArrayList<>(this.products.size());
        for (Double key : keys) {
            result.addAll(this.byPrice.get(key));
        }
        return result.size() == 0 ? new ArrayList<>() : result;
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return this.byPrice.getOrDefault(price, new ArrayList<>());
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        List<Product> result = new ArrayList<>(count);
        this.byPrice.values()
                .stream()
                .limit(count)
                .forEach(result::addAll);
        if(result.size() < count) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return this.byQuantity.getOrDefault(quantity, new ArrayList<>());
    }

    @Override
    public Iterator<Product> iterator() {
        return this.products.iterator();
    }
}
