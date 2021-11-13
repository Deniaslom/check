import service.Purchase;

public class Main {
    public static void main(String[] args) {

        Purchase purchase = new Purchase();

        String str = "2-5 3-1 4-1 5-5 6-1 7-1 8-5 9-1 10-5 card-1234";

        purchase.getCheck(str);

    }
}
