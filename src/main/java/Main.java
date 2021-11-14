import beans.CashReceipt;
import service.CashReceiptService;

public class Main {
    public static void main(String[] args) {

        CashReceiptService cashReceiptService = new CashReceiptService();

        String str = "2-10 3-1 4-1 5-5 6-1 7-1 8-5 9-1 10-5 card-1234";

        CashReceipt check = cashReceiptService.getCheck(str);

        System.out.println("11111111111111");
        System.out.println(check.getCreationTime());
        System.out.println("22222222222222");
        System.out.println(check.getDiscount());
        System.out.println("33333333333333");
        System.out.println(check.getEntries());
        System.out.println("44444444444444");
        System.out.println(check.getTotalPrice());

    }
}
