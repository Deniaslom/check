package services.straregies.impl;


import Parser.CashReceiptRequestParser;
import models.CashReceipt;
import models.CashReceiptRequest;
import org.junit.Before;
import org.junit.Test;
import repositories.impl.DiscountCartRepositoryImpl;
import repositories.impl.ProductRepositoryImpl;
import services.CashReceiptEntryService;
import services.CashReceiptService;
import services.impl.CashReceiptEntryServiceImpl;
import services.impl.CashReceiptServiceImpl;
import services.straregies.CashReceiptCalculationStrategy;

public class CashReceiptCalculationStrategyImplTest {
    private CashReceiptCalculationStrategy cashReceiptCalculationStrategy;
    private CashReceiptService cashReceiptService;
    private CashReceiptEntryService entryService;
    private CashReceipt cashReceipt;

    @Before
    public void before() {
        cashReceiptCalculationStrategy = new CashReceiptCalculationStrategyImpl();
        entryService = new CashReceiptEntryServiceImpl(new ProductRepositoryImpl(), new CashReceiptEntryCalculationStrategyImpl());
        cashReceiptService = new CashReceiptServiceImpl(entryService, new CashReceiptCalculationStrategyImpl(), new DiscountCartRepositoryImpl());

        String str = "1-6 2-50 3-20 4-10 5-11 6-11 7-30 8-10 9-11 10-511 card-1236";

        CashReceiptRequest request = CashReceiptRequestParser.getCashReceiptRequestParser(str);
        cashReceipt = cashReceiptService.getCashReceipt(request);
    }

    @Test
    public void calculate() {
        cashReceiptCalculationStrategy.calculate(cashReceipt);

        assert (cashReceipt.getTotalDiscount().doubleValue() == 6700.78);
        assert (cashReceipt.getTotalPrice().doubleValue() == 28013.48);
    }
}
