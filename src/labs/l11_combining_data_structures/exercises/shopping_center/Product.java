package labs.l11_combining_data_structures.exercises.shopping_center;

import java.math.BigDecimal;

public class Product implements Comparable<Product> {

    private String name;
    private BigDecimal price;
    private String producer;

    public Product(String name, BigDecimal price, String producer) {
        this.name = name;
        this.price = price;
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public int compareTo(Product o) {
        int compare = this.name.compareTo(o.getName());
        if(compare == 0) {
            compare = this.producer.compareTo(o.getProducer());
        }
        if(compare == 0) {
            compare = this.price.compareTo(o.getPrice());
        }
        return compare;
    }

    @Override
    public String toString() {
        return String.format("{%s;%s;%.2f}", this.getName(), this.getProducer(), this.getPrice());
    }

    @Override
    public boolean equals(Object obj) {
        if(this != obj) {
            return false;
        }
        if(!(obj instanceof Product)) {
            return false;
        }
        Product other = (Product) obj;
        return this.getName().equals(other.getName());
    }
}