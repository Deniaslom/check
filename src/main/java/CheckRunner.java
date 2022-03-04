import model.CashReceipt;
import model.CashReceiptRequest;
import parser.CashReceiptRequestParser;
import printers.CashReceiptPrinter;
import printers.impl.CashReceiptConsolePrinter;
import printers.impl.CashReceiptFilePrinter;
import printers.impl.CashReceiptPdfFilePrinter;
import serialization.impl.DefaultFormatCashReceipt;
import services.CashReceiptEntryService;
import services.CashReceiptService;
import services.impl.CashReceiptEntryServiceImpl;
import services.impl.CashReceiptServiceImpl;
import services.impl.DiscountCartServiceImpl;
import services.impl.ProductServiceImpl;
import services.straregies.impl.CashReceiptCalculationStrategyImpl;
import services.straregies.impl.CashReceiptEntryCalculationStrategyImpl;

import java.io.IOException;

public class CheckRunner {
    public static void main(String[] args) throws IOException {
        CashReceiptEntryService entryService = new CashReceiptEntryServiceImpl(new ProductServiceImpl(), new CashReceiptEntryCalculationStrategyImpl());
        CashReceiptService cashReceiptService = new CashReceiptServiceImpl(entryService, new CashReceiptCalculationStrategyImpl(), new DiscountCartServiceImpl());

        String str = String.join(" ", "1-20 2-30 3-3 4-11 5-11 6-11 7-11 8-81 9-11 10-51 card-1234");
//        String str = String.join(" ", args);

        CashReceiptRequest request = CashReceiptRequestParser.getCashReceiptRequestParser(str);
        CashReceipt check = cashReceiptService.getCashReceipt(request);

        CashReceiptPrinter consolePrinter = new CashReceiptConsolePrinter(new DefaultFormatCashReceipt());
        CashReceiptPrinter filePrinter = new CashReceiptFilePrinter(new DefaultFormatCashReceipt());
        CashReceiptPrinter pdfFilePrinter = new CashReceiptPdfFilePrinter();

        consolePrinter.print(check);
        filePrinter.print(check);
        pdfFilePrinter.print(check);

    }
}
