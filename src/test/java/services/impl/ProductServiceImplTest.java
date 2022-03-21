package services.impl;

import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ProductService;

import java.math.BigDecimal;

class ProductServiceImplTest {
    private ProductService productService = new ProductServiceImpl();
    private static Product product = null;
    BigDecimal price = new BigDecimal(1234.2).setScale(1, 1);

    @BeforeEach
    public void before(){
        product = new Product(null, "Super milk", price, true);
    }

    @Test
    public void findProductIdAfterAdd(){
        Product productFromDb = productService.save(product);
        assert (productFromDb.equals(productService.getProductById(productFromDb.getId())));
    }

    @Test
    public void updateProductAfterAdd(){
        Product productFromDb = productService.save(product);
        productFromDb.setName("Meat");
        productService.update(productFromDb.getId(), productFromDb);
        assert (productFromDb.equals(productService.getProductById(productFromDb.getId())));
    }


    @Test
    public void deleteProductAfterAdd(){
        Product productFromDb = productService.save(product);
        productService.delete(productFromDb.getId());
        assert (null == productService.getProductById(productFromDb.getId()));
    }

}