package utils;

import beans.DiscountCard;
import beans.Product;
import repositories.impl.DiscountCartRepositoryImpl;
import repositories.impl.ProductRepositoryImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import service.CashReceiptService;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsingStringCheck {
    private static final Logger LOGGER = Logger.getLogger(CashReceiptService.class);

    public static Map<Product, Integer> getProductsFromDB(String str) {
        Map<Product, Integer> products = new HashMap<>();

        Pattern pattern = Pattern.compile("(([0-9]+)-([0-9]+))+");
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            Product product = new ProductRepositoryImpl().getProducts().
                    stream().
                    filter(o -> o.getId() == Integer.parseInt(matcher.group(2))).
                    findFirst().get();
            products.put(product, Integer.valueOf(matcher.group(3)));
        }
        return products;

    }


    public static DiscountCard getClientCartFromDb(String str) {
        Pattern pattern1 = Pattern.compile("(card)-([1-9]+)");
        Matcher matcher1 = pattern1.matcher(str);
        DiscountCard card = null;
        while (matcher1.find()) {
            try {
                card = new DiscountCartRepositoryImpl().getDiscountCards().
                        stream().
                        filter(o -> o.getNumber() == Integer.parseInt(matcher1.group(2))).
                        findFirst().get();
            } catch (Exception e) {
                LOGGER.log(Level.ERROR, "cart not found: " + e);
            }
        }
        return card;
    }

}
