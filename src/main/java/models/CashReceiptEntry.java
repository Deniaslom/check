package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;


public class CashReceiptEntry {

    private int quantity;
    private String description;
    private BigDecimal price;
    private BigDecimal discount;
    private boolean isDiscount;
    private BigDecimal totalPrice;

    public CashReceiptEntry(Product product, int quantity){
        description = product.getName();
        price = product.getPrice();
        isDiscount = product.isDiscount();
        quantity = quantity;
    }

    public CashReceiptEntry(int quantity, String description, BigDecimal price, BigDecimal discount, boolean isDiscount, BigDecimal totalPrice) {
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.isDiscount = isDiscount;
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public boolean isDiscount() {
        return isDiscount;
    }

    public void setDiscount(boolean discount) {
        isDiscount = discount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
