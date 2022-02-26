package services;

import model.CashReceipt;
import model.CashReceiptRequest;

public interface CashReceiptService {

    CashReceipt getCashReceipt(CashReceiptRequest request);
}
