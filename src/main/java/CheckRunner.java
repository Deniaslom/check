import Deserialization.impl.DefaultFormatCashReceipt;
import Parser.CashReceiptRequestParser;
import models.CashReceipt;
import models.CashReceiptRequest;
import printers.CashReceiptPrinter;
import printers.impl.CashReceiptConsolePrinter;
import printers.impl.CashReceiptFilePrinter;
import repositories.DiscountCartRepository;
import repositories.ProductRepository;
import services.CashReceiptEntryService;
import services.CashReceiptService;
import services.DiscountCartService;
import services.impl.CashReceiptEntryServiceImpl;
import services.impl.CashReceiptServiceImpl;
import services.impl.DiscountCartServiceImpl;
import services.impl.ProductServiceImpl;
import services.straregies.impl.CashReceiptCalculationStrategyImpl;
import services.straregies.impl.CashReceiptEntryCalculationStrategyImpl;

public class CheckRunner {
    public static void main(String[] args) {
        CashReceiptEntryService entryService = new CashReceiptEntryServiceImpl(new ProductServiceImpl(), new CashReceiptEntryCalculationStrategyImpl());
        CashReceiptService cashReceiptService = new CashReceiptServiceImpl(entryService, new CashReceiptCalculationStrategyImpl(), new DiscountCartServiceImpl());

        String str = String.join(" ", "1-20 2-30 3-3 4-11 5-11 6-11 7-11 8-81 9-11 10-51 card-1234");

        CashReceiptRequest request = CashReceiptRequestParser.getCashReceiptRequestParser(str);
        CashReceipt check = cashReceiptService.getCashReceipt(request);

        CashReceiptPrinter consolePrinter = new CashReceiptConsolePrinter(new DefaultFormatCashReceipt());
        CashReceiptPrinter filePrinter = new CashReceiptFilePrinter(new DefaultFormatCashReceipt());

        consolePrinter.print(check);
        filePrinter.print(check);
    }
}
