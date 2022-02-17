package printers;

import models.CashReceipt;

import java.io.FileNotFoundException;

public interface CashReceiptPrinter {

    void print(CashReceipt cashReceipt) throws FileNotFoundException;
}
