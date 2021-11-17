package service.interfaces;

import models.Product;

import java.math.BigDecimal;

public interface CashReceiptEntryCanculationStrategy {
    BigDecimal productDiscount(Product product, int quantity);
    BigDecimal productPrice(Product product, int quantity);
}
