package aspect;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import caching.algoritm.Cache;
import factory.CacheFactory;
import model.Product;
import repositories.impl.ProductRepositoryImpl;

import java.util.Optional;


@Aspect
@Log
public class CacheAspect {
    private static final Cache<Object> cache = CacheFactory.getInstance().getCache();
    private ProductRepositoryImpl productRepository = new ProductRepositoryImpl();

    @AfterReturning(value = "execution(* services.impl.ProductServiceImpl.addProduct(..))", returning = "product")
    public void addProduct(Product product) {
        cache.set(product.getId(), product);
        log.info("add product in cache - " + product);
    }

    @Before(value = "execution(* services.impl.ProductServiceImpl.deleteProductById(..))")
    public void deleteProductById(JoinPoint jp) {
        cache.delete((Integer) jp.getArgs()[0]);
        log.info("delete product id - " + jp.getArgs()[0]);
    }

    @AfterReturning(value = "execution(* services.impl.ProductServiceImpl.updateProduct(..))", returning = "product")
    public void updateProduct(Product product) {
        cache.set(product.getId(), product);
        log.info("update product in cache - " + product);
    }

    @Around(value = "execution(* services.impl.ProductServiceImpl.getProductById(..))")
    public Object getProductById(ProceedingJoinPoint joinPoint) throws Throwable {
        int id = (int) joinPoint.getArgs()[0];
        Product product;

        if (cache.get(id) == null) {
            product = productRepository.getProducts().get(id);
            cache.set(product.getId(), product);
        }
        joinPoint.proceed();
        log.info("get product from cache by id = " + id);;
        return Optional.of(cache.get(id));
    }
}
