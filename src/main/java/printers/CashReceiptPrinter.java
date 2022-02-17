package printers;

import models.CashReceipt;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface CashReceiptPrinter {

    void print(CashReceipt cashReceipt) throws IOException;
}
