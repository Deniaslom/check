package beans;

import java.math.BigDecimal;
import java.util.Objects;

public class LineCheck {
    private int quantity;
    private Product product;
    private BigDecimal price;
    private BigDecimal discount;
    private BigDecimal totalLineCost;

    public LineCheck() {
    }

    public LineCheck(int quantity, Product product, BigDecimal price, BigDecimal discount, BigDecimal totalLineCost) {
        this.quantity = quantity;
        this.product = product;
        this.price = price;
        this.discount = discount;
        this.totalLineCost = totalLineCost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotalLineCost() {
        return totalLineCost;
    }

    public void setTotalLineCost(BigDecimal totalLineCost) {
        this.totalLineCost = totalLineCost;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineCheck lineCheck = (LineCheck) o;
        return quantity == lineCheck.quantity &&
                discount == lineCheck.discount &&
                Objects.equals(product, lineCheck.product) &&
                Objects.equals(price, lineCheck.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, product, price, discount);
    }
}
