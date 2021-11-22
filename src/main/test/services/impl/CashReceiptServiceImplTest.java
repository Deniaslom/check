package services.impl;


import Parser.CashReceiptRequestParser;
import models.CashReceipt;
import models.CashReceiptRequest;
import org.junit.Before;
import org.junit.Test;
import repositories.impl.DiscountCartRepositoryImpl;
import repositories.impl.ProductRepositoryImpl;
import services.CashReceiptEntryService;
import services.CashReceiptService;
import services.straregies.impl.CashReceiptCalculationStrategyImpl;
import services.straregies.impl.CashReceiptEntryCalculationStrategyImpl;

public class CashReceiptServiceImplTest {
    private CashReceiptEntryService entryService;
    private CashReceiptService cashReceiptService;
    private String str;
    private CashReceiptRequest request;

    @Before
    public void before(){
        entryService = new CashReceiptEntryServiceImpl(new ProductRepositoryImpl(), new CashReceiptEntryCalculationStrategyImpl());
        cashReceiptService =  new CashReceiptServiceImpl(entryService, new CashReceiptCalculationStrategyImpl(), new DiscountCartRepositoryImpl());
        str = "1-20 2-30 7-11 8-81 9-11 10-51 card-1234";
        request = CashReceiptRequestParser.getCashReceiptRequestParser(str);
    }

    @Test
    public void getCashReceipt(){
        CashReceipt check = cashReceiptService.getCashReceipt(request);
        System.out.println(check.getTotalPrice());
        System.out.println(check.getTotalDiscount());
        System.out.println(check.getEntries().size());
        System.out.println(check.getCard().getNumber());

        assert (check.getTotalPrice().doubleValue() == 5906.23);
        assert (check.getTotalDiscount().doubleValue() == 1282.13);
        assert (check.getEntries().size() == 6);
        assert (check.getCard().getNumber().equals(1234));
    }


}
