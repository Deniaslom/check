package serializations.impl;

import serializations.CashReceiptSerialization;
import models.CashReceipt;
import models.CashReceiptEntry;

public class DefaultFormatCashReceipt implements CashReceiptSerialization {

    @Override
    public String deserialize(CashReceipt cashReceipt) {
        String title = String.format("%35s\n", "======= CASH RECEIPT =======");
        String time_location = String.format("Time: " + cashReceipt.getCreationTime() + "\n");
        String table_header = String.format("%3s %13s  %7s  %9s  %6s\n", "QTY", "DESCRIPTION", "PRICE", "DISCOUNT", "TOTAL");
        StringBuilder entriesBuilder = new StringBuilder();
        for (CashReceiptEntry entry : cashReceipt.getEntries()) {
            entriesBuilder.append(String.format("%3d %11s %10.2f %9.2f %10.2f\n", entry.getQuantity(),
                    entry.getProduct().getName(),
                    entry.getProduct().getPrice(),
                    entry.getDiscount(),
                    entry.getTotalPrice()));
        }
        String discount_with_card = String.format("\n    Total discount with card: " + cashReceipt.getTotalDiscount() + "\n");
        String info_card = String.format("Cart: %d, discount: %.2f%%\n", cashReceipt.getCard().getNumber(), cashReceipt.getCard().getDiscount());

        String total_price = String.format("Total price: " + cashReceipt.getTotalPrice());

        String cashReceiptOutput2 = title + time_location + table_header + entriesBuilder + discount_with_card + info_card + total_price;

        StringBuilder cashReceiptOutput = new StringBuilder();

        cashReceiptOutput.append(title);
        cashReceiptOutput.append(time_location);
        cashReceiptOutput.append(table_header);
        cashReceiptOutput.append(entriesBuilder);
        cashReceiptOutput.append(discount_with_card);
        cashReceiptOutput.append(info_card);
        cashReceiptOutput.append(total_price);

        return cashReceiptOutput2;
    }
}
