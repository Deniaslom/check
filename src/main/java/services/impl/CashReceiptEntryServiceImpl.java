package services.impl;

import model.CashReceiptEntry;
import model.CashReceiptRequest;
import model.Product;
import services.CashReceiptEntryService;
import services.ProductService;
import services.straregies.CashReceiptEntryCalculationStrategy;

import java.util.*;

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
            Product product = null;
            try {
                product = productService.getProductById(entry.getKey());  //почему требует try-catch
            } catch (Exception e) {
                e.printStackTrace();
            }
            CashReceiptEntry cashReceiptEntry = new CashReceiptEntry();
            cashReceiptEntry.setQuantity(entry.getValue());
            cashReceiptEntry.setProduct(product);
            cashReceiptEntryCalculationStrategy.calculate(cashReceiptEntry);
            cashReceiptEntries.add(cashReceiptEntry);
        }

        return cashReceiptEntries;
    }
}
