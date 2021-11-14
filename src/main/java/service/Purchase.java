package service;

import beans.CashReceipt;
import org.apache.log4j.Logger;
import utils.CreateLineCheck;

import java.util.Date;


public class Purchase {
    private static final Logger LOGGER = Logger.getLogger(service.Purchase.class);

    public Purchase() {
    }

    public CashReceipt getCheck(String str){
        CashReceipt cashReceipt = new CashReceipt();

        cashReceipt.setCreationTime(new Date());
       // cashReceipt.set(CreateLineCheck.getLinesChecks(str));
        cashReceipt.setTotalCost(CreateLineCheck.totalCost(CreateLineCheck.getLinesChecks(str)));
        System.out.println(cashReceipt.getDate());
        return cashReceipt;
    }



}
