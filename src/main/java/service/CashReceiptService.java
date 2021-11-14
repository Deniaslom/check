package service;

import beans.CashReceipt;
import org.apache.log4j.Logger;
import utils.CreateLineCheck;

import java.time.LocalDateTime;


public class CashReceiptService {
    private static final Logger LOGGER = Logger.getLogger(CashReceiptService.class);

    public CashReceiptService() {
    }

    public CashReceipt getCheck(String str){
        CashReceipt cashReceipt = new CashReceipt();

        cashReceipt.setCreationTime(LocalDateTime.now());
        cashReceipt.setEntries(CreateLineCheck.getLinesChecks(str));
//        cashReceipt.setDiscount(CreateLineCheck.totalCost(CreateLineCheck.getLinesChecks(str)));
        cashReceipt.setTotalPrice(CreateLineCheck.totalCost(CreateLineCheck.getLinesChecks(str)));
        return cashReceipt;
    }



}
