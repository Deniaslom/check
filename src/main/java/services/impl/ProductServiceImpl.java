package services.impl;

import model.Product;
import repositories.ProductRepository;
import repositories.impl.ProductRepositoryImpl;
import services.ProductService;

public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository = new ProductRepositoryImpl();

    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }

    public Product addProduct(int id, Product product) {
        productRepository.addProduct(product);
        return product;
    }

    public void deleteProductById(int id) {
        productRepository.deleteProductById(id);
    }

}
