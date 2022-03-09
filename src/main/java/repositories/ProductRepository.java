package repositories;

import model.Product;

import java.util.Map;

public interface ProductRepository {

//    Map<Integer, Product> getProducts();

    Product getProductById(int id);

    Product save(Product product);

    void delete(int id);

    Product update(int id, Product product);
}
