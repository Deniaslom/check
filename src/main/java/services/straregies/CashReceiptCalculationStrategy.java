package services.straregies;

import models.CashReceipt;

public interface CashReceiptCalculationStrategy {

    void calculate(CashReceipt cashReceipt);
}
