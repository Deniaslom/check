package printers.impl;

import models.CashReceipt;
import models.CashReceiptEntry;
import printers.CashReceiptPrinter;

public class CashReceiptConsolePrinter implements CashReceiptPrinter {
    @Override
    public void print(CashReceipt cashReceipt) {
        System.out.printf("%35s\n","======= CASH RECEIPT =======");
        System.out.println("Time: " + cashReceipt.getCreationTime());
        System.out.printf("%3s %13s  %7s  %9s  %6s\n","QTY","DESCRIPTION","PRICE","DISCOUNT","TOTAL");
        for(CashReceiptEntry entry : cashReceipt.getEntries()){
            System.out.printf("%3d %11s %10.2f %9.2f %10.2f\n", entry.getQuantity(),
                    entry.getProduct().getName(),
                    entry.getProduct().getPrice(),
                    entry.getDiscount(),
                    entry.getTotalPrice());
        }
        System.out.println("\n    Total discount with card: " + cashReceipt.getTotalDiscount());
        System.out.printf("Cart: %d, discount: %.2f%%\n", cashReceipt.getCard().getNumber(),cashReceipt.getCard().getDiscount());

        System.out.println("Total price: " + cashReceipt.getTotalPrice());
    }
}
