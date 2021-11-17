package services.straregies.impl;

import models.CashReceiptEntry;
import services.straregies.CashReceiptEntryCalculationStrategy;

import java.math.BigDecimal;

public class CashReceiptEntryCalculationStrategyImpl implements CashReceiptEntryCalculationStrategy {

    @Override
    public void calculate(CashReceiptEntry receiptEntry) {
        BigDecimal discount = BigDecimal.ZERO;
        BigDecimal totalPrice;

        if(receiptEntry.isDiscount() && receiptEntry.getQuantity() > 5){
            discount = receiptEntry.getPrice().multiply(new BigDecimal(receiptEntry.getQuantity())).multiply(new BigDecimal(0.1));
            totalPrice = receiptEntry.getPrice().multiply(new BigDecimal(receiptEntry.getQuantity())).multiply(new BigDecimal(0.9));
        } else {
            totalPrice = receiptEntry.getPrice().multiply(new BigDecimal(receiptEntry.getQuantity()));
        }
        receiptEntry.setDiscount(discount);
        receiptEntry.setTotalPrice(totalPrice);
    }
}
