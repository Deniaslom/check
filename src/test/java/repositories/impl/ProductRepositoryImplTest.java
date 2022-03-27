package repositories.impl;


import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.ProductRepository;

import java.math.BigDecimal;

class ProductRepositoryImplTest {
    private ProductRepository repository = new ProductRepositoryImpl();
    private static Product product = null;
    private static Product productOptional = new Product();
    BigDecimal price = new BigDecimal(1234.2).setScale(1, 1);

    @BeforeEach
    public void before(){
        product = new Product(null, "Super milk", price, true);
    }

    @Test
    public void findProductIdAfterAdd(){
        Product productFromDb = repository.save(product);
        assert (productFromDb.equals(repository.getProductById(productFromDb.getId())));
    }

    @Test
    public void updateProductAfterAdd(){
        Product productFromDb = repository.save(product);
        productFromDb.setName("Meat");
        repository.update(productFromDb.getId(), productFromDb);
        assert (productFromDb.equals(repository.getProductById(productFromDb.getId())));
    }

    @Test
    public void deleteProductAfterAdd(){
        Product productFromDb = repository.save(product);
        repository.delete(productFromDb.getId());
        assert (productOptional.equals(repository.getProductById(productFromDb.getId())));
    }

}