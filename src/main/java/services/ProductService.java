package services;

import model.Product;

import java.util.Optional;

public interface ProductService {
    public Optional<Product> getProductById(Integer id);

    public Product addProduct(int id, Product product);

    public void deleteProductById(int id);

    public Product updateProduct(int id, Product product);
}
