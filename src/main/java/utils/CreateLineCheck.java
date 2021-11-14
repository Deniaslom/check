package utils;

import beans.CashReceiptEntry;
import beans.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateLineCheck {
    public static List<CashReceiptEntry> getLinesChecks(String str){
        List<CashReceiptEntry> cashReceiptEntries = new ArrayList<>();
        Map<Product, Integer> productsFromDB = ParsingStringCheck.getProductsFromDB(str);

        for (Map.Entry<Product, Integer> entry : productsFromDB.entrySet()) {
            cashReceiptEntries.add(new CashReceiptEntry(entry.getValue(),
                                         entry.getKey(),
                                         entry.getKey().getPrice(),
                    productDiscount(entry.getKey(), entry.getValue()),
                    productCost(entry.getKey(),entry.getValue())));

        }

        return cashReceiptEntries;
    }


    public static BigDecimal productDiscount(Product product, int quantity){
        BigDecimal productDiscount = BigDecimal.ZERO;
        if(product.isDiscount() && quantity > 5){
            productDiscount = productDiscount.multiply(new BigDecimal(0.05));
        }
        return productDiscount;
    }

    public static BigDecimal productCost(Product product, int quantity){
        return product.getPrice().multiply(new BigDecimal(quantity)).subtract(productDiscount(product, quantity));
    }

    public static BigDecimal totalCost(List<CashReceiptEntry> cashReceiptEntries){
        BigDecimal totalCost = BigDecimal.ZERO;
        for(CashReceiptEntry cashReceiptEntry : cashReceiptEntries){
            totalCost = totalCost.add(cashReceiptEntry.getTotalLineCost());
        }
        return totalCost;
    }

}
