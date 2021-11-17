package service.impl;

import models.CashReceiptEntry;
import models.Product;
import repositories.ProductRepository;
import repositories.impl.ProductRepositoryImpl;
import service.interfaces.CashReceiptEntryCanculationStrategy;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CashReceiptEntryServiceImpl {
    private final static ProductRepository productRepository = new ProductRepositoryImpl();
    private final static CashReceiptEntryCanculationStrategy CANCULATION_STRATEGY = new CashReceiptEntryCanculationStrategyImpl();

    public List<CashReceiptEntry> getListCashReceiptEntries(String str){
        List<CashReceiptEntry> cashReceiptEntries = new ArrayList<>();
        Map<Product, Integer> products = getListProductsByCheck(str);

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            cashReceiptEntries.add(new CashReceiptEntry(
                    entry.getValue(),
                    entry.getKey().getName(),
                    entry.getKey().getPrice().setScale(2, RoundingMode.HALF_UP),
                    CANCULATION_STRATEGY.productDiscount(entry.getKey(), entry.getValue()),
                    CANCULATION_STRATEGY.productPrice(entry.getKey(),entry.getValue())));
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
}
