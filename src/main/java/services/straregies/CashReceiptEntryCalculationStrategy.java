package services.straregies;

import model.CashReceiptEntry;

public interface CashReceiptEntryCalculationStrategy {

    void calculate(CashReceiptEntry receiptEntry);
}
