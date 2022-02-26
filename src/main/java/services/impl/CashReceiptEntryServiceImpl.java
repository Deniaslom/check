package services.impl;

import model.CashReceiptEntry;
import model.CashReceiptRequest;
import model.Product;
import services.CashReceiptEntryService;
import services.ProductService;
import services.straregies.CashReceiptEntryCalculationStrategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CashReceiptEntryServiceImpl implements CashReceiptEntryService {

    private ProductService productService;
    private CashReceiptEntryCalculationStrategy cashReceiptEntryCalculationStrategy;

    public CashReceiptEntryServiceImpl(ProductService productService, CashReceiptEntryCalculationStrategy cashReceiptEntryCalculationStrategy) {
        this.productService = productService;
        this.cashReceiptEntryCalculationStrategy = cashReceiptEntryCalculationStrategy;
    }

    @Override
    public List<CashReceiptEntry> getCashReceiptEntries(CashReceiptRequest request) {
        List<CashReceiptEntry> cashReceiptEntries = new ArrayList<>();

        Map<Integer, Integer> productsWithQuantity = request.getProductsWithQuantity();
        Iterator<Map.Entry<Integer, Integer>> iterator = productsWithQuantity.entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry<Integer, Integer> entry = iterator.next();
            Product product = productService.getProductById(entry.getKey()).orElseThrow(() -> new NullPointerException("product is missing by ID = " + entry.getKey()));

            CashReceiptEntry cashReceiptEntry = new CashReceiptEntry();
            cashReceiptEntry.setQuantity(entry.getValue());
            cashReceiptEntry.setProduct(product);
            cashReceiptEntryCalculationStrategy.calculate(cashReceiptEntry);
            cashReceiptEntries.add(cashReceiptEntry);
        }

        return cashReceiptEntries;
    }
}
