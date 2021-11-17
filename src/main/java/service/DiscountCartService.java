package service;

import beans.DiscountCard;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import repositories.DiscountCartRepository;
import repositories.impl.DiscountCartRepositoryImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiscountCartService {
    private static final Logger LOGGER = Logger.getLogger(DiscountCartService.class);

    private static final DiscountCartRepository DISCOUNT_CART = new DiscountCartRepositoryImpl();


    public DiscountCard getDiscountCardFromCheck(String str){
        Pattern pattern1 = Pattern.compile("(card)-([1-9]+)");
        Matcher matcher1 = pattern1.matcher(str);
        DiscountCard card = null;
        while (matcher1.find()) {
            try {
                card = DISCOUNT_CART.getDiscountCardByNumber(Integer.parseInt(matcher1.group(2))).get();
            } catch (Exception e) {
                LOGGER.log(Level.ERROR, "cart not found: " + e);
            }
        }
        return card;
    }
}
