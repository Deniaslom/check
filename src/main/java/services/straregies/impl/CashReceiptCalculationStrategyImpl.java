package services.straregies.impl;

import models.CashReceipt;
import models.CashReceiptEntry;
import models.DiscountCard;
import services.straregies.CashReceiptCalculationStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CashReceiptCalculationStrategyImpl implements CashReceiptCalculationStrategy {

    @Override
    public void calculate(CashReceipt cashReceipt) {
        BigDecimal totalPriceWithDiscount = BigDecimal.ZERO;
        BigDecimal totalPriceWithOutDiscount = BigDecimal.ZERO;
        BigDecimal totalPrice;
        BigDecimal totalDiscount;

        List<CashReceiptEntry> entries = cashReceipt.getEntries();
        DiscountCard discountCart = cashReceipt.getCard();     //orElse throw

        for (CashReceiptEntry entry : entries) {
            totalPriceWithDiscount = totalPriceWithDiscount
                    .add(entry.getTotalPrice());
            totalPriceWithOutDiscount = totalPriceWithOutDiscount.add(entry.getProduct().getPrice().multiply(new BigDecimal(entry.getQuantity())));
        }

        totalPrice = totalPriceWithDiscount.multiply(new BigDecimal((100 - discountCart.getDiscount()) / 100));
        totalDiscount = totalPriceWithOutDiscount.subtract(totalPrice);

        cashReceipt.setTotalPrice(totalPrice.setScale(2, RoundingMode.HALF_UP));
        cashReceipt.setTotalDiscount(totalDiscount.setScale(2, RoundingMode.HALF_UP));
    }
}
