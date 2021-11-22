package printers.impl;

import Deserialization.CashReceiptDeserialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import models.CashReceipt;
import printers.CashReceiptPrinter;

import java.io.FileWriter;
import java.io.IOException;

@Data
@AllArgsConstructor
public class CashReceiptFilePrinter implements CashReceiptPrinter {
    private CashReceiptDeserialization cashReceiptDeserialization;

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
