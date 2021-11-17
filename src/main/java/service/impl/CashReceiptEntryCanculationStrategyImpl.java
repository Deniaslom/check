package service.impl;

import models.Product;
import service.interfaces.CashReceiptEntryCanculationStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CashReceiptEntryCanculationStrategyImpl implements CashReceiptEntryCanculationStrategy {
    @Override
    public BigDecimal productDiscount(Product product, int quantity) {
        BigDecimal  productDiscount = BigDecimal.ZERO;
        if(product.isDiscount() && quantity > 5){
            productDiscount = product.getPrice().multiply(new BigDecimal(quantity)).multiply(new BigDecimal(0.1));
        }
        return productDiscount.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal productPrice(Product product, int quantity) {
        BigDecimal productPrice;
        if(product.isDiscount() && quantity > 5){
            productPrice = product.getPrice().multiply(new BigDecimal(quantity)).multiply(new BigDecimal(0.9));
        } else{
            productPrice = product.getPrice().multiply(new BigDecimal(quantity));
        }
        return productPrice.setScale(2, RoundingMode.HALF_UP);
    }
}
