package services.straregies.impl;


import Parser.CashReceiptRequestParser;
import models.CashReceipt;
import models.CashReceiptRequest;
import org.junit.Before;
import org.junit.Test;
import repositories.DiscountCartRepository;
import repositories.ProductRepository;
import repositories.impl.DiscountCartRepositoryImpl;
import repositories.impl.ProductRepositoryImpl;
import services.CashReceiptEntryService;
import services.CashReceiptService;
import services.impl.CashReceiptEntryServiceImpl;
import services.impl.CashReceiptServiceImpl;
import services.straregies.CashReceiptCalculationStrategy;
import services.straregies.CashReceiptEntryCalculationStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class CashReceiptCalculationStrategyImplTest {

    private ProductRepository productRepository = new ProductRepositoryImpl();
    private DiscountCartRepository cartRepository = new DiscountCartRepositoryImpl();
    private CashReceiptEntryCalculationStrategy entryCalculationStrategy = new CashReceiptEntryCalculationStrategyImpl();
    private CashReceiptCalculationStrategy calculationStrategy = new CashReceiptCalculationStrategyImpl();

    private CashReceiptEntryService cashReceiptEntryService = new CashReceiptEntryServiceImpl(productRepository, entryCalculationStrategy);
    private CashReceiptService cashReceiptService = new CashReceiptServiceImpl(cashReceiptEntryService, calculationStrategy, cartRepository);
    private CashReceipt cashReceipt;

    @Before
    public void before() {
    }

    @Test
    public void shouldCalculateTotalPriceAndDiscountWhenProductsAllFindAndCart() {
        String requestString = "1-20 2-30 3-3 4-11 5-11 6-11 7-11 8-81 9-11 10-51 card-1234";
        cashReceipt = getCashReceiptByString(requestString);

        assertEquals(cashReceipt.getTotalPrice(), getPrice(7565.85));
        assertEquals(cashReceipt.getTotalDiscount(), getPrice(1569.93));
    }

    @Test
    public void shouldCalculateTotalPriceAndDiscountWhenProductsWithOneQuantityWithCart(){
        String requestString = "1-1 2-1 3-1 4-1 5-1 6-1 7-1 8-1 9-1 10-1 card-1234";
        cashReceipt = getCashReceiptByString(requestString);

        assertEquals(cashReceipt.getTotalPrice(), getPrice(656.93));
        assertEquals(cashReceipt.getTotalDiscount(), getPrice(93.85));
    }

    @Test
    public void shouldCalculateTotalPriceAndDiscountWhenProductsWithOneQuantityWithOutCart(){
        String requestString = "1-1 2-1 3-1 4-1 5-1 6-1 7-1 8-1 9-1 10-1";
        cashReceipt = getCashReceiptByString(requestString);

        assertEquals(cashReceipt.getTotalPrice(), getPrice(750.78));
        assertEquals(cashReceipt.getTotalDiscount(), getPrice(0));
    }

    @Test
    public void shouldCalculateTotalPriceAndDiscountWhenProductMoreFiveQuantityWithCart(){
        String requestString = "1-11 2-6 3-7 4-6 5-7 6-8 7-9 8-6 9-8 10-10 card-1237";
        cashReceipt = getCashReceiptByString(requestString);

        assertEquals(cashReceipt.getTotalPrice(), getPrice(5185.33));
        assertEquals(cashReceipt.getTotalDiscount(), getPrice(653.99));
    }

    @Test
    public void shouldCalculateTotalPriceAndDiscountWhenProductMoreFiveQuantityWithOutCart(){
        String requestString = "1-11 2-6 3-7 4-6 5-7 6-8 7-9 8-6 9-8 10-10";
        cashReceipt = getCashReceiptByString(requestString);

        assertEquals(cashReceipt.getTotalPrice(), getPrice(5729.65));
        assertEquals(cashReceipt.getTotalDiscount(), getPrice(109.67));
    }

    private BigDecimal getPrice(double price) {
        return new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
    }

    private CashReceipt getCashReceiptByString(String requestString){
        CashReceiptRequest request = CashReceiptRequestParser.getCashReceiptRequestParser(requestString);
        return cashReceiptService.getCashReceipt(request);
    }
}
