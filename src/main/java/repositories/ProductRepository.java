package repositories;

import beans.Product;

import java.util.Collection;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> getProductById(Integer id);
    Collection<Product> getProducts();
}
