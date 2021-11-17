package repositories.impl;

import models.Product;
import repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.*;

//@Data
//@NoArgsConstructor
public final class ProductRepositoryImpl implements ProductRepository {

    private static final Map<Integer, Product> PRODUCTS;

    static {
        PRODUCTS = new HashMap<>();
        PRODUCTS.put(1, new Product(1, "milk", new BigDecimal(123.12), false));
        PRODUCTS.put(2, new Product(2, "apple", new BigDecimal(57), true));
        PRODUCTS.put(3, new Product(3, "meat", new BigDecimal(432), false));
        PRODUCTS.put(4, new Product(4, "Orange", new BigDecimal(34), true));
        PRODUCTS.put(5, new Product(5, "cucumbers", new BigDecimal(13.12), false));
        PRODUCTS.put(6, new Product(6, "bananas", new BigDecimal(12.1), true));
        PRODUCTS.put(7, new Product(7, "Cherry", new BigDecimal(23.2), false));
        PRODUCTS.put(8, new Product(8, "pineapple", new BigDecimal(7.12), true));
        PRODUCTS.put(9, new Product(9, "crisps", new BigDecimal(8.12), false));
        PRODUCTS.put(10, new Product(10, "tangerines", new BigDecimal(41.12), true));
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        return Optional.ofNullable(PRODUCTS.get(id));  //.orElseThrow()
    }

    @Override
    public Map<Integer, Product> getProducts() {
        return PRODUCTS;
    }
}
