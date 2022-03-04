package services.straregies.impl;

import model.CashReceiptEntry;
import services.straregies.CashReceiptEntryCalculationStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CashReceiptEntryCalculationStrategyImpl implements CashReceiptEntryCalculationStrategy {

    @Override
    public void calculate(CashReceiptEntry receiptEntry) {
        BigDecimal discount = BigDecimal.ZERO;
        BigDecimal totalPrice;

        if (receiptEntry.getProduct().isDiscount() && receiptEntry.getQuantity() > 5) {
            discount = receiptEntry.getProduct().getPrice().multiply(new BigDecimal(receiptEntry.getQuantity())).multiply(new BigDecimal(0.1));
            totalPrice = receiptEntry.getProduct().getPrice().multiply(new BigDecimal(receiptEntry.getQuantity())).multiply(new BigDecimal(0.9));
        } else {
            totalPrice = receiptEntry.getProduct().getPrice().multiply(new BigDecimal(receiptEntry.getQuantity()));
        }
        receiptEntry.setDiscount(discount.setScale(2, RoundingMode.HALF_UP));
        receiptEntry.setTotalPrice(totalPrice.setScale(2, RoundingMode.HALF_UP));
    }
}
