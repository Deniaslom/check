package printers.impl;

import Deserialization.CashReceiptDeserialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import models.CashReceipt;
import printers.CashReceiptPrinter;

@Data
@AllArgsConstructor
public class CashReceiptConsolePrinter implements CashReceiptPrinter {
    private CashReceiptDeserialization cashReceiptDeserialization;

    @Override
    public void print(CashReceipt cashReceipt) {
        System.out.println(cashReceiptDeserialization.deserialize(cashReceipt));
    }
}
