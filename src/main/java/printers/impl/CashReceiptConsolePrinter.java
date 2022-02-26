package printers.impl;

import serializations.CashReceiptSerializator;
import lombok.AllArgsConstructor;
import lombok.Data;
import model.CashReceipt;
import printers.CashReceiptPrinter;

@Data
@AllArgsConstructor
public class CashReceiptConsolePrinter implements CashReceiptPrinter {
    private CashReceiptSerializator cashReceiptDeserialization;

    @Override
    public void print(CashReceipt cashReceipt) {
        System.out.println(cashReceiptDeserialization.deserialize(cashReceipt));
    }
}
