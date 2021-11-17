package repositories;

import models.Product;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> getProductById(Integer id);
}
