package printers.impl;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import model.CashReceipt;
import model.CashReceiptEntry;
import printers.CashReceiptPrinter;

import java.io.IOException;
import java.util.List;

public class CashReceiptPdfFilePrinter implements CashReceiptPrinter {
    private static PdfDocument backPdfDocument = null;
    private static PdfDocument receiptPdfDocument = null;

    @Override
    public void print(CashReceipt check) throws IOException {
        backPdfDocument = new PdfDocument(new PdfReader("clevertec.pdf"));
        receiptPdfDocument = new PdfDocument(new PdfWriter("cash_receipt.pdf"));
        receiptPdfDocument.addNewPage();

        Document document = new Document(receiptPdfDocument);
        document.add(getInfoTable());
        document.add(getProductsTable(check));
        document.add(getInfoTotalPurchase(check));


        PdfCanvas canvas = new PdfCanvas(receiptPdfDocument.getFirstPage().newContentStreamBefore(),
                receiptPdfDocument.getFirstPage().getResources(), receiptPdfDocument);

        PdfFormXObject pdfFormXObject = backPdfDocument.getFirstPage().copyAsFormXObject(receiptPdfDocument);
        canvas.addXObjectAt(pdfFormXObject, 0, 0);

        backPdfDocument.close();
        receiptPdfDocument.close();
        document.close();
    }


    private Table getInfoTable() {
        Table table = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth().setMarginTop(100);

        table.setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setWidth(UnitValue.createPercentValue(100))
                .setFontSize(16f);

        Cell cashReceipt = new Cell(1, 5).add(new Paragraph("======= CASH RECEIPT =======").setTextAlignment(TextAlignment.CENTER));
        Cell qty = new Cell(1, 1).add(new Paragraph("QTY"));
        Cell description = new Cell(1, 1).add(new Paragraph("DESCRIPTION"));
        Cell price = new Cell(1, 1).add(new Paragraph("PRICE"));
        Cell discount = new Cell(1, 1).add(new Paragraph("DISCOUNT"));
        Cell total = new Cell(1, 1).add(new Paragraph("TOTAL"));

        table.addCell(cashReceipt);
        table.addCell(qty);
        table.addCell(description);
        table.addCell(price);
        table.addCell(discount);
        table.addCell(total);

        return table;
    }

    private Table getProductsTable(CashReceipt check) {
        List<CashReceiptEntry> listProducts = check.getEntries();
        Table tableProducts = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();

        for (CashReceiptEntry product : listProducts) {
            Cell qty = new Cell(1, 1).add(new Paragraph(String.valueOf(product.getQuantity())));
            Cell description = new Cell(1, 1).add(new Paragraph(String.valueOf(product.getProduct().getName())));
            Cell price = new Cell(1, 1).add(new Paragraph(String.valueOf(product.getProduct().getPrice())));
            Cell discount = new Cell(1, 1).add(new Paragraph(String.valueOf(product.getDiscount())));
            Cell total = new Cell(1, 1).add(new Paragraph(String.valueOf(product.getTotalPrice())));

            tableProducts.addCell(qty);
            tableProducts.addCell(description);
            tableProducts.addCell(price);
            tableProducts.addCell(discount);
            tableProducts.addCell(total);
        }

        return tableProducts;
    }

    private Table getInfoTotalPurchase(CashReceipt check) {
        Table totalPurchase = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();

        Cell totalDiscount = new Cell(2, 2).
                add(new Paragraph(String.valueOf("Total discount with card: " + check.getTotalDiscount())));
        Cell cart = new Cell(1, 1).add(new Paragraph(String.valueOf("Cart: " + check.getCard().getNumber())));
        Cell discount = new Cell(1, 1).add(new Paragraph("discount: " + String.valueOf(check.getCard().getDiscount() + "%")));
        Cell totalPrice = new Cell(1, 1).add(new Paragraph("Total price: " + check.getTotalPrice()));

        totalPurchase.addCell(totalDiscount);
        totalPurchase.addCell(cart);
        totalPurchase.addCell(discount);
        totalPurchase.addCell(totalPrice);

        return totalPurchase;
    }


}
