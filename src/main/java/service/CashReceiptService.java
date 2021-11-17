package service;

import models.CashReceipt;
import models.CashReceiptEntry;
import models.DiscountCard;
import org.apache.log4j.Logger;
import service.impl.CashReceiptEntryServiceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;


public class CashReceiptService {
    private static final Logger LOGGER = Logger.getLogger(CashReceiptService.class);
    private static final CashReceiptEntryServiceImpl ENTRY_SERVICE = new CashReceiptEntryServiceImpl();
    private static final DiscountCartService DISCOUNT_CART_SERVICE = new DiscountCartService();

    public CashReceiptService() {
    }

    public CashReceipt getCheck(String str) {
        CashReceipt cashReceipt = new CashReceipt();

        cashReceipt.setCreationTime(LocalDateTime.now());
        cashReceipt.setEntries(ENTRY_SERVICE.getListCashReceiptEntries(str));
        cashReceipt.setTotalDiscount(totalDiscount(str));
        cashReceipt.setTotalPrice(totalPrice(str));

        return cashReceipt;
    }


    public static BigDecimal totalDiscount(String str) {
        List<CashReceiptEntry> entries = ENTRY_SERVICE.getListCashReceiptEntries(str);
        BigDecimal totalPriceWithDiscount = BigDecimal.ZERO;
        DiscountCard discountCart = DISCOUNT_CART_SERVICE.getDiscountCardFromCheck(str);

        for (CashReceiptEntry entry : entries) {
            totalPriceWithDiscount = totalPriceWithDiscount.add(entry.getTotalPrice());
        }
        totalPriceWithDiscount = totalPriceWithDiscount.multiply(new BigDecimal((100 - discountCart.getDiscount()) / 100));

        BigDecimal totalPriceWithOutDiscount = BigDecimal.ZERO;

        for (CashReceiptEntry entry : entries) {
            totalPriceWithOutDiscount = totalPriceWithOutDiscount.add(entry.getPrice().multiply(new BigDecimal(entry.getQuantity())));
        }

        System.out.println("totalPriceWithOutDiscount = " + totalPriceWithOutDiscount);
        totalPriceWithOutDiscount = totalPriceWithOutDiscount.subtract(totalPriceWithDiscount);
        return totalPriceWithOutDiscount.setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal totalPrice(String str) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<CashReceiptEntry> cashReceiptEntries = ENTRY_SERVICE.getListCashReceiptEntries(str);
        for (CashReceiptEntry cashReceiptEntry : cashReceiptEntries) {
            totalPrice = totalPrice.add(cashReceiptEntry.getTotalPrice());
        }
        DiscountCard discountCart = DISCOUNT_CART_SERVICE.getDiscountCardFromCheck(str);
        totalPrice = totalPrice.multiply(new BigDecimal((100 - discountCart.getDiscount()) / 100));
        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }


}
