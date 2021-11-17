package calculations;

import models.CashReceiptRequest;

import java.math.BigDecimal;

public interface CashReceiptCalculationStrategy {
    BigDecimal totalDiscount(CashReceiptRequest request);
    BigDecimal totalPrice(CashReceiptRequest request);
}
