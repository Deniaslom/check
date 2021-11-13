package beans;


import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private boolean discount;

    public Product(){
    }

    public Product(int id, String name, BigDecimal price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(int id, String name, BigDecimal price, boolean discount){
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                discount == product.discount &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, discount);
    }

    @Override
    public String toString() {
        return getId() + ";" + getName() + ";" + getPrice() + ";";
    }
}
