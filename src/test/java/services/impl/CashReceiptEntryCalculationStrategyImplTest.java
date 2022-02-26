package services.impl;


import model.CashReceiptEntry;
import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.straregies.CashReceiptEntryCalculationStrategy;
import services.straregies.impl.CashReceiptEntryCalculationStrategyImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashReceiptEntryCalculationStrategyImplTest {

    private static final Integer PRODUCT_ID = 5;
    private static final String PRODUCT_NAME = "milk";
    private static final BigDecimal PRODUCT_PRICE = new BigDecimal(10);
    private static final int PRODUCT_QUANTITY = 6;

    private CashReceiptEntryCalculationStrategy calculationStrategy = new CashReceiptEntryCalculationStrategyImpl();
    private CashReceiptEntry cashReceiptEntry;
    private Product product;

    @BeforeEach
    public void before() {
        cashReceiptEntry = new CashReceiptEntry();
        product= new Product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, FALSE);
        cashReceiptEntry.setProduct(product);
        cashReceiptEntry.setQuantity(PRODUCT_QUANTITY);
    }

    @Test
    public void shouldCalculateWhenProductIsNotDiscount(){
        calculationStrategy.calculate(cashReceiptEntry);

        assertEquals(cashReceiptEntry.getTotalPrice(), getPrice(60));
        assertEquals(cashReceiptEntry.getDiscount(), getPrice(0));
    }

    @Test
    public void shouldCalculateWhenProductIsDiscountAndQuantityMoreThan5(){
        product.setDiscount(TRUE);
        calculationStrategy.calculate(cashReceiptEntry);

        assertEquals(cashReceiptEntry.getTotalPrice(), getPrice(54));
        assertEquals(cashReceiptEntry.getDiscount(), getPrice(6));
    }


    @Test
    public void shouldCalculateTotalWhenProductIsDiscountAndQuantityLessThan5(){
        product.setDiscount(TRUE);
        cashReceiptEntry.setQuantity(3);
        calculationStrategy.calculate(cashReceiptEntry);

        assertEquals(cashReceiptEntry.getTotalPrice(), getPrice(30));
        assertEquals(cashReceiptEntry.getDiscount(), getPrice(0));

    }

    private BigDecimal getPrice(double price){
        return new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
    }
}
