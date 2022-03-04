package printers.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.CashReceipt;
import printers.CashReceiptPrinter;
import serialization.CashReceiptSerializator;

@Data
@AllArgsConstructor
public class CashReceiptConsolePrinter implements CashReceiptPrinter {
    private CashReceiptSerializator cashReceiptDeserialization;

    @Override
    public void print(CashReceipt cashReceipt) {
        System.out.println(cashReceiptDeserialization.deserialize(cashReceipt));
    }
}
