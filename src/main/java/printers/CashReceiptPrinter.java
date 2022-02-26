package printers;

import model.CashReceipt;

import java.io.IOException;

public interface CashReceiptPrinter {

    void print(CashReceipt cashReceipt) throws IOException;
}
