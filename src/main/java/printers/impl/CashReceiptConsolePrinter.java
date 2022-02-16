package printers.impl;

import serializations.CashReceiptSerialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import models.CashReceipt;
import printers.CashReceiptPrinter;

@Data
@AllArgsConstructor
public class CashReceiptConsolePrinter implements CashReceiptPrinter {
    private CashReceiptSerialization cashReceiptDeserialization;

    @Override
    public void print(CashReceipt cashReceipt) {
        System.out.println(cashReceiptDeserialization.deserialize(cashReceipt));
    }
}
