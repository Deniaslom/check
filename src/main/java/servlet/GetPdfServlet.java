package servlet;

import model.CashReceipt;
import model.CashReceiptRequest;
import parser.CashReceiptRequestParser;
import printers.CashReceiptPrinter;
import printers.impl.CashReceiptPdfFilePrinter;
import services.CashReceiptEntryService;
import services.CashReceiptService;
import services.impl.CashReceiptEntryServiceImpl;
import services.impl.CashReceiptServiceImpl;
import services.impl.DiscountCartServiceImpl;
import services.impl.ProductServiceImpl;
import services.straregies.impl.CashReceiptCalculationStrategyImpl;
import services.straregies.impl.CashReceiptEntryCalculationStrategyImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/GetPdfReceipt")
public class GetPdfServlet extends HttpServlet {
    private CashReceiptEntryService entryService = new CashReceiptEntryServiceImpl(new ProductServiceImpl(), new CashReceiptEntryCalculationStrategyImpl());
    private CashReceiptService cashReceiptService = new CashReceiptServiceImpl(entryService, new CashReceiptCalculationStrategyImpl(), new DiscountCartServiceImpl());
    private CashReceiptPrinter pdfFilePrinter = new CashReceiptPdfFilePrinter();
    private static final String ORDERS = "orders";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CashReceiptRequest orderRequest = CashReceiptRequestParser.getCashReceiptRequestParser(request.getParameter(ORDERS));
        CashReceipt check = cashReceiptService.getCashReceipt(orderRequest);

        //1-20 3-30 6-3 7-11 8-11 9-11 15-11 16-81 17-11 18-51 card-261
        pdfFilePrinter.print(check);

        byte[] buffer = Files.readAllBytes(Paths.get("D:\\programming\\clevertec_tasks\\clevertec_01_11_21\\clevertec\\cash_receipt.pdf"));

        response.setContentLength(buffer.length);
        response.getOutputStream().write(buffer);

    }
}
