package services.impl;

import model.Product;
import repositories.impl.ProductRepositoryImpl;
import services.ProductService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private ProductRepositoryImpl productRepository = new ProductRepositoryImpl();

    public Optional<Product> getProductById(Integer id) {
        return Optional.ofNullable(productRepository.getProducts().get(id));  //.orElseThrow()
    }

    public Product addProduct(int id, Product product) {
        productRepository.getProducts().put(id, product);
        return product;
    }

    public void deleteProductById(int id) {
        productRepository.getProducts().remove(id);
    }

    public Product updateProduct(int id, Product product) {
        productRepository.getProducts().put(id, product);
        return product;
    }

    private static BigDecimal getBigDecimal(double price) {
        return new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
    }
}
