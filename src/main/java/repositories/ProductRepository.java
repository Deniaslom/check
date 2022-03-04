package repositories;

import model.Product;

import java.util.Map;

public interface ProductRepository {
    Map<Integer, Product> getProducts();
    Product getProductById(int id);
    boolean addProduct(Product product);
    void deleteProductById(int id);
}
