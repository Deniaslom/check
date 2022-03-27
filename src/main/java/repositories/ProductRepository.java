package repositories;

import model.Product;

import java.util.List;
import java.util.Map;

public interface ProductRepository {

//    Map<Integer, Product> getProducts();

    Product getProductById(int id);

    Product save(Product product);

    void delete(int id);

    Product update(int id, Product product);

    List<Product> findAll(int size, int page);
}
