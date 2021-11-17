package services;

import models.CashReceipt;
import models.CashReceiptRequest;

public interface CashReceiptService {

    CashReceipt getCashReceipt(CashReceiptRequest request);
}
