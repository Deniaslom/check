import Deserialization.impl.DefaultFormatCashReceipt;
import Parser.CashReceiptRequestParser;
import models.CashReceipt;
import models.CashReceiptRequest;
import printers.CashReceiptPrinter;
import printers.impl.CashReceiptConsolePrinter;
import printers.impl.CashReceiptFilePrinter;
import repositories.impl.DiscountCartRepositoryImpl;
import repositories.impl.ProductRepositoryImpl;
import services.CashReceiptEntryService;
import services.CashReceiptService;
import services.impl.CashReceiptEntryServiceImpl;
import services.impl.CashReceiptServiceImpl;
import services.straregies.impl.CashReceiptCalculationStrategyImpl;
import services.straregies.impl.CashReceiptEntryCalculationStrategyImpl;

public class CheckRunner {
    public static void main(String[] args) {
        CashReceiptEntryService entryService = new CashReceiptEntryServiceImpl(new ProductRepositoryImpl(), new CashReceiptEntryCalculationStrategyImpl());

        CashReceiptService cashReceiptService = new CashReceiptServiceImpl(entryService, new CashReceiptCalculationStrategyImpl(), new DiscountCartRepositoryImpl());

//        String str = "1-20 2-30 3-3 4-11 5-11 6-11 7-11 8-81 9-11 10-51 card-1234";
        String str = "1-20 2-30 3-3 4-11 5-11 6-11 7-11 8-81 9-11 10-51";
//        String str = String.join(" ", args);

        CashReceiptRequest request = CashReceiptRequestParser.getCashReceiptRequestParser(str);
        CashReceipt check = cashReceiptService.getCashReceipt(request);

        CashReceiptPrinter consolePrinter = new CashReceiptConsolePrinter(new DefaultFormatCashReceipt());
        CashReceiptPrinter filePrinter = new CashReceiptFilePrinter(new DefaultFormatCashReceipt());

        consolePrinter.print(check);
        filePrinter.print(check);
    }
}
