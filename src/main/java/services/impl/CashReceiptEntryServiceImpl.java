package service.impl;

import models.CashReceiptEntry;
import models.CashReceiptRequest;
import models.Product;
import repositories.ProductRepository;
import repositories.impl.ProductRepositoryImpl;
import service.interfaces.CashReceiptEntryCanculationStrategy;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CashReceiptEntryServiceImpl {
    private final static ProductRepository productRepository = new ProductRepositoryImpl();
    private final static CashReceiptEntryCanculationStrategy CALCULATION_STRATEGY  = new CashReceiptEntryCalculationStrategyImpl();

    public List<CashReceiptEntry> getListCashReceiptEntries(CashReceiptRequest request){
        List<CashReceiptEntry> cashReceiptEntries = new ArrayList<>();
        Map<Product, Integer> products = getListProductsByCheck(request);

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            cashReceiptEntries.add(new CashReceiptEntry(
                    entry.getValue(),
                    entry.getKey().getName(),
                    entry.getKey().getPrice().setScale(2, RoundingMode.HALF_UP),
                    CALCULATION_STRATEGY.productDiscount(entry.getKey(), entry.getValue()),
                    CALCULATION_STRATEGY.productPrice(entry.getKey(),entry.getValue())));
        }

        return cashReceiptEntries;
    }

    public static Map<Product, Integer> getListProductsByCheck(CashReceiptRequest request){
        Map<Product, Integer> listProductsByCheck = new HashMap<>();
        for(Map.Entry<Integer, Integer> productFromCheck : request.getProductsWithQuantity().entrySet()){
            Product productFromDb = productRepository.getProductById(productFromCheck.getKey()).get();
            Integer quantity = productFromCheck.getValue();
            listProductsByCheck.put(productFromDb, quantity);
        }
        return listProductsByCheck;
    }
}
