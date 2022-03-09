package caching.algoritm;

import org.junit.jupiter.api.BeforeEach;
import model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class LRUCacheTest {
    private LRUCache cache = new LRUCache(2);
    private final Product product_0 = new Product(0, "Product_0",null,true);
    private final Product product_1 = new Product(1, "Product_1",null,true);
    private final Product product_2 = new Product(2, "Product_2",null,true);
    private final Product product_3 = new Product(3, "Product_3",null,true);
    private final Product product_4 = new Product(4, "Product_4",null,true);
    private final Product product_5 = new Product(5, "Product_5",null,true);
    private final Product product_6 = new Product(6, "Product_6",null,true);
    private final Product product_7 = new Product(7, "Product_7",null,true);

    @BeforeEach
    public void before() {
        cache.set(0, product_0);
        cache.set(1, product_1);
        cache.set(2, product_2);
        cache.set(3, product_3);
        cache.set(4, product_4);
    }

    @Test
    public void checkEntityAfterAdd() {
        assertAll(
                () -> assertFalse(cache.get(0) == product_0),
                () -> assertFalse(cache.get(1) == product_1),
                () -> assertFalse(cache.get(2) == product_2),
                () -> assertTrue(cache.get(3) == product_3),
                () -> assertTrue(cache.get(4) == product_4),
                () -> assertFalse(cache.get(5) == product_5));
    }

    @Test
    public void checkSize() {
        assert (cache.size() == 2);
    }

    @Test
    public void checkSizeAfterFalseDelete() {
        cache.delete(2);
        assert (cache.size() == 2);
    }

    @Test
    public void checkSizeAfterDelete() {
        cache.delete(3);
        assert (cache.size() == 1);
    }

    @Test
    public void checkSizeAfterAdd() {
        cache.set(5, product_5);
        cache.set(6, product_6);
        cache.set(7, product_7);
        assert (cache.size() == 2);
    }

    @Test
    public void checkAdd() {
        cache.set(0, product_0);
        cache.set(1, product_1);

        assert (cache.get(0) == product_0);
        assert (cache.get(1) == product_1);
    }


}