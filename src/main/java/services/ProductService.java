package services;

import model.Product;

public interface ProductService {

    Product getProductById(int id);

    Product save(Product product);

    void delete(int id);

    void update(int id, Product product);
}
