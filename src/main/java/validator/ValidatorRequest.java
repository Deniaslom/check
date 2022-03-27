package validator;

import model.Product;
import repositories.ProductRepository;
import repositories.impl.ProductRepositoryImpl;

public class ValidatorRequest {
    private final static ProductRepository productRepository = new ProductRepositoryImpl();

    public static boolean isInt(String integer) {
        try {
            int intValue = Integer.parseInt(integer);
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Incorrect request: " + e.getMessage());
        }
    }

    public static boolean isProductIdByDb(Integer id) {
        Product product = productRepository.getProductById(id);
        if (product.getId() == null)
            return true;
        return false;
    }
}
