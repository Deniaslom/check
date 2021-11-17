package services.straregies;

import models.CashReceiptEntry;

public interface CashReceiptEntryCalculationStrategy {

    void calculate(CashReceiptEntry receiptEntry);
}
