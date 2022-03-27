package services;

import model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll(int size, int page);

    Product getProductById(int id);

    Product save(Product product);

    void delete(int id);

    void update(int id, Product product);
}
