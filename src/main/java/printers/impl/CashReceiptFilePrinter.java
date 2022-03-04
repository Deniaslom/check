package printers.impl;

import serialization.CashReceiptSerializator;
import lombok.AllArgsConstructor;
import lombok.Data;
import model.CashReceipt;
import printers.CashReceiptPrinter;

import java.io.FileWriter;
import java.io.IOException;

@Data
@AllArgsConstructor
public class CashReceiptFilePrinter implements CashReceiptPrinter {
    private CashReceiptSerializator cashReceiptDeserialization;

    @Override
    public void print(CashReceipt cashReceipt) {
        try(FileWriter writer = new FileWriter("cash_receipt.txt", false)){
            writer.write(cashReceiptDeserialization.deserialize(cashReceipt));
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
