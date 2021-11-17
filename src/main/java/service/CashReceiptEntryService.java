package service;

import beans.CashReceiptEntry;
import beans.Product;
import repositories.ProductRepository;
import repositories.impl.ProductRepositoryImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CashReceiptEntryService {
    private final static ProductRepository productRepository = new ProductRepositoryImpl();

    public List<CashReceiptEntry> getListCashReceiptEntries(String str){
        List<CashReceiptEntry> cashReceiptEntries = new ArrayList<>();
        Map<Product, Integer> productsFromDB = getListProductsByCheck(str);

        for (Map.Entry<Product, Integer> entry : productsFromDB.entrySet()) {
            cashReceiptEntries.add(new CashReceiptEntry(
                    entry.getValue(),
                    entry.getKey().getName(),
                    entry.getKey().getPrice().setScale(2, RoundingMode.HALF_UP),
                    productDiscount(entry.getKey(), entry.getValue()),
                    productPrice(entry.getKey(),entry.getValue())));
        }

        return cashReceiptEntries;
    }

    public static Map<Product, Integer> getListProductsByCheck(String str){
        Map<Product, Integer> listProductsByCheck = new HashMap<>();

        Pattern pattern = Pattern.compile("(([0-9]+)-([0-9]+))+");
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            Product productFromDb = productRepository.getProductById(Integer.valueOf(matcher.group(2))).get();
            Integer quantity = Integer.valueOf(matcher.group(3));

            listProductsByCheck.put(productFromDb, quantity);
        }
        return listProductsByCheck;
    }


    public static BigDecimal productDiscount(Product product, int quantity){
        BigDecimal  productDiscount = BigDecimal.ZERO;
        if(product.isDiscount() && quantity > 5){
            productDiscount = product.getPrice().multiply(new BigDecimal(quantity)).multiply(new BigDecimal(0.05));
        }
        return productDiscount.setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal productPrice(Product product, int quantity){
        BigDecimal productPrice;
        if(product.isDiscount() && quantity > 5){
            productPrice = product.getPrice().multiply(new BigDecimal(quantity)).subtract(productDiscount(product, quantity));
        } else{
            productPrice = product.getPrice().multiply(new BigDecimal(quantity));
        }
        return productPrice.setScale(2, RoundingMode.HALF_UP);
    }

}
