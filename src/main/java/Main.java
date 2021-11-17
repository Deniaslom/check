import models.CashReceipt;
import service.CashReceiptService;

public class Main {
    public static void main(String[] args) {

        CashReceiptService cashReceiptService = new CashReceiptService();

        String str = "1-20 2-30 3-3 4-11 5-11 6-11 7-11 8-81 9-11 10-51 card-1234";

        CashReceipt check = cashReceiptService.getCheck(str);

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
