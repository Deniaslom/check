package Parser;

import models.CashReceiptRequest;
import models.DiscountCard;
import models.Product;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import service.DiscountCartService;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CashReceiptRequestParserImpl implements CashReceiptRequestParser {
    private static final Logger LOGGER = Logger.getLogger(DiscountCartService.class);



    @Override
    public CashReceiptRequest getCashReceiptRequestParser(String str) {
        CashReceiptRequest request = new CashReceiptRequest();

        request.setProductsWithQuantity(getListProductsByCheck(str));
        request.setIdCard(getDiscountCardFromCheck(str));

        return null;
    }

    private Map<Integer, Integer> getListProductsByCheck(String str){
        Map<Integer, Integer> listProductsByCheck = new HashMap<>();

        Pattern pattern = Pattern.compile("(([0-9]+)-([0-9]+))+");
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            Integer productId = Integer.valueOf(matcher.group(2));
            Integer quantity = Integer.valueOf(matcher.group(3));

            listProductsByCheck.put(productId, quantity);
        }
        return listProductsByCheck;
    }

    private Integer getDiscountCardFromCheck(String str){
        Pattern pattern1 = Pattern.compile("(card)-([1-9]+)");
        Matcher matcher1 = pattern1.matcher(str);
        Integer card = null;
        while (matcher1.find()) {
            try {
                card = Integer.parseInt(matcher1.group(2));
            } catch (Exception e) {
                LOGGER.log(Level.ERROR, "cart not found: " + e);
            }
        }
        return card;
    }
}
