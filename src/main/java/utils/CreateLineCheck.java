package utils;

import beans.CashReceiptEntry;
import beans.DiscountCard;
import beans.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateLineCheck {
    public static List<CashReceiptEntry> getLinesCheck(String str){
        List<CashReceiptEntry> cashReceiptEntries = new ArrayList<>();
        Map<Product, Integer> productsFromDB = ParsingStringCheck.getProductsFromDB(str);

        for (Map.Entry<Product, Integer> entry : productsFromDB.entrySet()) {
            cashReceiptEntries.add(new CashReceiptEntry(entry.getValue(),
                                         entry.getKey().getName(),
                                         entry.getKey().getPrice(),
                    productDiscount(entry.getKey(), entry.getValue()),
                    productPrice(entry.getKey(),entry.getValue())));
        }

        return cashReceiptEntries;
    }


    public static BigDecimal productDiscount(Product product, int quantity){
        BigDecimal  productDiscount = BigDecimal.ZERO;
        if(product.isDiscount() && quantity > 5){
            productDiscount = product.getPrice().multiply(new BigDecimal(quantity)).multiply(new BigDecimal(0.05));
        }
        return productDiscount;
    }

    public static BigDecimal productPrice(Product product, int quantity){
        BigDecimal productPrice;
        if(product.isDiscount() && quantity > 5){
            productPrice = product.getPrice().multiply(new BigDecimal(quantity)).subtract(productDiscount(product, quantity));
        } else{
            productPrice = product.getPrice().multiply(new BigDecimal(quantity));
        }
        return productPrice;
    }

    public static BigDecimal totalDiscount(String str) {
        List<CashReceiptEntry> entries = CreateLineCheck.getLinesCheck(str);
        BigDecimal totalPriceWithDiscount = BigDecimal.ZERO;

        for (CashReceiptEntry entry : entries) {
            totalPriceWithDiscount = totalPriceWithDiscount.add(entry.getTotalPrice());
        }

        BigDecimal totalPriceWithOutDiscount = BigDecimal.ZERO;

        for (CashReceiptEntry entry : entries) {
            totalPriceWithOutDiscount = totalPriceWithOutDiscount.add(entry.getPrice().multiply(new BigDecimal(entry.getQuantity())));
        }
        return totalPriceWithOutDiscount.subtract(totalPriceWithDiscount);
    }

    public static BigDecimal totalPrice(String str){
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<CashReceiptEntry> cashReceiptEntries = CreateLineCheck.getLinesCheck(str);
        for(CashReceiptEntry cashReceiptEntry : cashReceiptEntries){
            totalPrice = totalPrice.add(cashReceiptEntry.getTotalPrice());
        }
        DiscountCard discountCart = ParsingStringCheck.getClientCartFromDb(str);
            totalPrice.multiply(new BigDecimal((100-discountCart.getDiscount())/100));

        return totalPrice;
    }

}
