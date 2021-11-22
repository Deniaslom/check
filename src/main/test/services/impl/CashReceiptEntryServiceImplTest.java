package services.impl;


import Parser.CashReceiptRequestParser;
import models.CashReceiptEntry;
import models.CashReceiptRequest;
import org.junit.Before;
import org.junit.Test;
import repositories.impl.ProductRepositoryImpl;
import services.CashReceiptEntryService;
import services.straregies.impl.CashReceiptEntryCalculationStrategyImpl;

import java.util.List;

public class CashReceiptEntryServiceImplTest {
    private CashReceiptEntryService entryService;
    private CashReceiptRequest request;

    @Before
    public void before() {
        entryService = new CashReceiptEntryServiceImpl(new ProductRepositoryImpl(), new CashReceiptEntryCalculationStrategyImpl());
        String str = "1-1 2-2 3-3 4-4 5-5 6-6 7-7 8-8 9-9 10-10 card-1236";
        request = CashReceiptRequestParser.getCashReceiptRequestParser(str);
    }

    @Test
    public void getCashReceiptEntries(){
        List<CashReceiptEntry> cashReceiptEntries = entryService.getCashReceiptEntries(request);

        assert (cashReceiptEntries.get(0).getProduct().getId().equals(1));
        assert (cashReceiptEntries.get(1).getProduct().getId().equals(2));
        assert (cashReceiptEntries.get(2).getProduct().getId().equals(3));
        assert (cashReceiptEntries.get(3).getProduct().getId().equals(4));
        assert (cashReceiptEntries.get(4).getProduct().getId().equals(5));
        assert (cashReceiptEntries.get(5).getProduct().getId().equals(6));
        assert (cashReceiptEntries.get(6).getProduct().getId().equals(7));
        assert (cashReceiptEntries.get(7).getProduct().getId().equals(8));
        assert (cashReceiptEntries.get(8).getProduct().getId().equals(9));
        assert (cashReceiptEntries.get(9).getProduct().getId().equals(10));

        assert (cashReceiptEntries.get(0).getQuantity() == 1);
        assert (cashReceiptEntries.get(1).getQuantity() == 2);
        assert (cashReceiptEntries.get(2).getQuantity() == 3);
        assert (cashReceiptEntries.get(3).getQuantity() == 4);
        assert (cashReceiptEntries.get(4).getQuantity() == 5);
        assert (cashReceiptEntries.get(5).getQuantity() == 6);
        assert (cashReceiptEntries.get(6).getQuantity() == 7);
        assert (cashReceiptEntries.get(7).getQuantity() == 8);
        assert (cashReceiptEntries.get(8).getQuantity() == 9);
        assert (cashReceiptEntries.get(9).getQuantity() == 10);
    }
}
