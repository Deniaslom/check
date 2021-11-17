package calculations.impl;

import calculations.CashReceiptCalculationStrategy;
import models.CashReceiptEntry;
import models.CashReceiptRequest;
import models.DiscountCard;
import repositories.DiscountCartRepository;
import repositories.impl.DiscountCartRepositoryImpl;
import services.impl.CashReceiptEntryServiceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CashReceiptCalculationStrategyImpl implements CashReceiptCalculationStrategy {
    private static final CashReceiptEntryServiceImpl ENTRY_SERVICE = new CashReceiptEntryServiceImpl();
    private static final DiscountCartRepository CART_REPOSITORY = new DiscountCartRepositoryImpl();

    @Override
    public BigDecimal totalDiscount(CashReceiptRequest request) {
        List<CashReceiptEntry> entries = ENTRY_SERVICE.getListCashReceiptEntries(request);
        BigDecimal totalPriceWithDiscount = BigDecimal.ZERO;
        DiscountCard discountCart = CART_REPOSITORY.getDiscountCardByNumber(request.getIdCard()).get(); //orElse throw

        for (CashReceiptEntry entry : entries) {
            totalPriceWithDiscount = totalPriceWithDiscount.add(entry.getTotalPrice());
        }
        totalPriceWithDiscount = totalPriceWithDiscount.multiply(new BigDecimal((100 - discountCart.getDiscount()) / 100));

        BigDecimal totalPriceWithOutDiscount = BigDecimal.ZERO;

        for (CashReceiptEntry entry : entries) {
            totalPriceWithOutDiscount = totalPriceWithOutDiscount.add(entry.getPrice().multiply(new BigDecimal(entry.getQuantity())));
        }

        totalPriceWithOutDiscount = totalPriceWithOutDiscount.subtract(totalPriceWithDiscount);
        return totalPriceWithOutDiscount.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal totalPrice(CashReceiptRequest request) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<CashReceiptEntry> cashReceiptEntries = ENTRY_SERVICE.getListCashReceiptEntries(request);
        for (CashReceiptEntry cashReceiptEntry : cashReceiptEntries) {
            totalPrice = totalPrice.add(cashReceiptEntry.getTotalPrice());
        }
        DiscountCard discountCart = CART_REPOSITORY.getDiscountCardByNumber(request.getIdCard()).get(); //throw
        totalPrice = totalPrice.multiply(new BigDecimal((100 - discountCart.getDiscount()) / 100));
        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }
}
