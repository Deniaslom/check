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

    public Product save(Product product) {
        productRepository.save(product);
        return product;
    }

    public void delete(int id) {
        productRepository.delete(id);
    }

    @Override
    public void update(int id, Product product) {
        productRepository.update(id, product);
    }

}
