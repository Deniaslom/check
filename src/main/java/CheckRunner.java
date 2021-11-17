import Parser.CashReceiptRequestParser;
import models.CashReceipt;
import models.CashReceiptRequest;
import repositories.impl.DiscountCartRepositoryImpl;
import repositories.impl.ProductRepositoryImpl;
import services.CashReceiptService;
import services.impl.CashReceiptEntryServiceImpl;
import services.impl.CashReceiptServiceImpl;
import services.straregies.impl.CashReceiptCalculationStrategyImpl;
import services.straregies.impl.CashReceiptEntryCalculationStrategyImpl;

public class CheckRunner {
    public static void main(String[] args) {
        CashReceiptService cashReceiptService = new CashReceiptServiceImpl(
                new CashReceiptEntryServiceImpl(new ProductRepositoryImpl(), new CashReceiptEntryCalculationStrategyImpl()),
                new CashReceiptCalculationStrategyImpl(), new DiscountCartRepositoryImpl());

        String str = "1-20 2-30 3-3 4-11 5-11 6-11 7-11 8-81 9-11 10-51 card-1234";
//        String str = "2-20 card-1234";



        CashReceiptRequest request = CashReceiptRequestParser.getCashReceiptRequestParser(str);
        CashReceipt check = cashReceiptService.getCashReceipt(request);

        System.out.println("11111111111111");
        System.out.println(check.getCreationTime());
        System.out.println("22222222222222");
        System.out.println(check.getEntries());
        System.out.println("33333333333333");
        System.out.println(check.getTotalDiscount());
        System.out.println("44444444444444");
        System.out.println(check.getTotalPrice());

    }
}
