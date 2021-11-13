package generation;

import beans.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GenerationProductsDB {
    private static volatile GenerationProductsDB instance;
    private List<Product> products = new ArrayList<>();

    private GenerationProductsDB() {
        setProducts();
    }

    public static GenerationProductsDB getInstance(){
        if(instance == null)
            synchronized (GenerationProductsDB.class){
                if(instance == null)
                    instance = new GenerationProductsDB();
            }

        return instance;
    }

    private List<Product> setProducts() {

        products.add(new Product(1, "milk", new BigDecimal(123.12), false));
        products.add(new Product(2, "apple", new BigDecimal(57), true));
        products.add(new Product(3, "meat", new BigDecimal(432), false));
        products.add(new Product(4, "Orange", new BigDecimal(34), true));
        products.add(new Product(5, "cucumbers", new BigDecimal(13.12), false));
        products.add(new Product(6, "bananas", new BigDecimal(12.1), true));
        products.add(new Product(7, "Cherry", new BigDecimal(23.2), false));
        products.add(new Product(8, "pineapple", new BigDecimal(7.12), true));
        products.add(new Product(9, "crisps", new BigDecimal(8.12), false));
        products.add(new Product(10, "tangerines", new BigDecimal(41.12), true));

        return products;
    }

    public List<Product> getProducts(){
        return products;
    }

}
