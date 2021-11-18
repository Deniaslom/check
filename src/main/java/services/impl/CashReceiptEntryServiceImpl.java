package services.impl;

import models.CashReceiptEntry;
import models.CashReceiptRequest;
import models.Product;
import repositories.ProductRepository;
import services.CashReceiptEntryService;
import services.straregies.CashReceiptEntryCalculationStrategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CashReceiptEntryServiceImpl implements CashReceiptEntryService {

    private ProductRepository productRepository;
    private CashReceiptEntryCalculationStrategy cashReceiptEntryCalculationStrategy;

    public CashReceiptEntryServiceImpl(ProductRepository productRepository, CashReceiptEntryCalculationStrategy cashReceiptEntryCalculationStrategy) {
        this.productRepository = productRepository;
        this.cashReceiptEntryCalculationStrategy = cashReceiptEntryCalculationStrategy;
    }

    @Override
    public List<CashReceiptEntry> getCashReceiptEntries(CashReceiptRequest request) {
        List<CashReceiptEntry> cashReceiptEntries = new ArrayList<>();

        Map<Integer, Integer> productsWithQuantity = request.getProductsWithQuantity();
        Iterator<Map.Entry<Integer, Integer>> iterator = productsWithQuantity.entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry<Integer, Integer> entry = iterator.next();
            Product product = productRepository.getProductById(entry.getKey()).get();  //throw
            CashReceiptEntry cashReceiptEntry = new CashReceiptEntry();
            cashReceiptEntry.setQuantity(entry.getValue());
            cashReceiptEntry.setProduct(product);
            cashReceiptEntryCalculationStrategy.calculate(cashReceiptEntry);
            cashReceiptEntries.add(cashReceiptEntry);
        }

        return cashReceiptEntries;
    }
}
