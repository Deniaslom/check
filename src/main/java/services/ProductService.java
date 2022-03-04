package services;

import model.Product;

public interface ProductService {

    Product getProductById(int id) throws Exception;

    Product addProduct(int id, Product product);

    void deleteProductById(int id);

    Product updateProduct(int id, Product product);
}
