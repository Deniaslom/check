package services.straregies.impl;


import models.CashReceiptEntry;
import models.Product;
import org.junit.Before;
import org.junit.Test;
import services.straregies.CashReceiptEntryCalculationStrategy;

import java.math.BigDecimal;

public class CashReceiptEntryCalculationStrategyImplTest {
    private CashReceiptEntryCalculationStrategy cashReceiptEntryCalculationStrategy;
    private CashReceiptEntry cashReceiptEntry;

    @Before
    public void before() {
        cashReceiptEntryCalculationStrategy = new CashReceiptEntryCalculationStrategyImpl();
        cashReceiptEntry = new CashReceiptEntry();
    }

    @Test
    public void calculateWithoutDiscount() {
        Product product = new Product(5, "milk", new BigDecimal(10), false);

        cashReceiptEntry.setProduct(product);
        cashReceiptEntry.setQuantity(5);

        cashReceiptEntryCalculationStrategy.calculate(cashReceiptEntry);

        assert (cashReceiptEntry.getTotalPrice().intValue() == 50);
        assert (cashReceiptEntry.getDiscount().intValue() == 0);
    }

    @Test
    public void calculateWithDiscount() {
        Product product = new Product(5, "meat", new BigDecimal(100), true);

        cashReceiptEntry.setProduct(product);
        cashReceiptEntry.setQuantity(30);

        cashReceiptEntryCalculationStrategy.calculate(cashReceiptEntry);

        assert (cashReceiptEntry.getTotalPrice().doubleValue() == 2700);
        assert (cashReceiptEntry.getDiscount().doubleValue() == 300);
    }
}
