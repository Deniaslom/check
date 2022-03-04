package repositories.impl;

import model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


public final class ProductRepositoryImpl {

    private static final Map<Integer, Product> PRODUCTS;

    static {
        PRODUCTS = new HashMap<>();
        PRODUCTS.put(1, new Product(1, "milk", getBigDecimal(123), false));
        PRODUCTS.put(2, new Product(2, "apple", getBigDecimal(57), true));
        PRODUCTS.put(3, new Product(3, "meat", getBigDecimal(432), false));
        PRODUCTS.put(4, new Product(4, "Orange", getBigDecimal(34), true));
        PRODUCTS.put(5, new Product(5, "cucumbers", getBigDecimal(13.12), false));
        PRODUCTS.put(6, new Product(6, "bananas", getBigDecimal(12.1), true));
        PRODUCTS.put(7, new Product(7, "Cherry", getBigDecimal(23.2), false));
        PRODUCTS.put(8, new Product(8, "pineapple", getBigDecimal(7.12), true));
        PRODUCTS.put(9, new Product(9, "crisps", getBigDecimal(8.12), false));
        PRODUCTS.put(10, new Product(10, "tangerines", getBigDecimal(41.12), true));
    }

    public Map<Integer, Product> getProducts(){
        return PRODUCTS;
    }

    private static BigDecimal getBigDecimal(double price) {
        return new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
    }
}
