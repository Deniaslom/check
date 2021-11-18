package printers.impl;

import models.CashReceipt;
import models.CashReceiptEntry;
import printers.CashReceiptPrinter;

import java.io.FileWriter;
import java.io.IOException;

public class CashReceiptFilePrinter implements CashReceiptPrinter {
    @Override
    public void print(CashReceipt cashReceipt) {
        try(FileWriter writer = new FileWriter("cash_receipt.txt", false)){
            String title = String.format("%35s\n","======= CASH RECEIPT =======");
            String time_location = String.format("Time: " + cashReceipt.getCreationTime() + "\n");
            String table_header = String.format("%3s %13s  %7s  %9s  %6s\n","QTY","DESCRIPTION","PRICE","DISCOUNT","TOTAL");
            StringBuilder entriesBuilder = new StringBuilder();
            for(CashReceiptEntry entry : cashReceipt.getEntries()){
                entriesBuilder.append(String.format("%3d %11s %10.2f %9.2f %10.2f\n", entry.getQuantity(),
                        entry.getProduct().getName(),
                        entry.getProduct().getPrice(),
                        entry.getDiscount(),
                        entry.getTotalPrice()));
            }
            String entries = entriesBuilder.toString();
            String discount_with_card = String.format("\n    Total discount with card: " + cashReceipt.getTotalDiscount() + "\n");
            String info_card = String.format("Cart: %d, discount: %.2f%%\n", cashReceipt.getCard().getNumber(),cashReceipt.getCard().getDiscount());

            String total_price = String.format("Total price: " + cashReceipt.getTotalPrice());

            writer.write(title);
            writer.write(time_location);
            writer.write(table_header);
            writer.write(entries);
            writer.write(discount_with_card);
            writer.write(info_card);
            writer.write(total_price);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
