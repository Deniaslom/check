package services.impl;

import model.CashReceipt;
import model.CashReceiptRequest;
import model.DiscountCard;
import services.CashReceiptEntryService;
import services.CashReceiptService;
import services.DiscountCartService;
import services.straregies.CashReceiptCalculationStrategy;

import java.time.LocalDateTime;


public class CashReceiptServiceImpl implements CashReceiptService {

    private CashReceiptEntryService cashReceiptEntryService;
    private CashReceiptCalculationStrategy cashReceiptCalculationStrategy;
    private DiscountCartService discountCartService;


    public CashReceiptServiceImpl(CashReceiptEntryService cashReceiptEntryService, CashReceiptCalculationStrategy cashReceiptCalculationStrategy, DiscountCartService discountCartService) {
        this.cashReceiptEntryService = cashReceiptEntryService;
        this.cashReceiptCalculationStrategy = cashReceiptCalculationStrategy;
        this.discountCartService = discountCartService;
    }

    @Override
    public CashReceipt getCashReceipt(CashReceiptRequest request) {
        CashReceipt cashReceipt = new CashReceipt();

        cashReceipt.setCreationTime(LocalDateTime.now());
        cashReceipt.setEntries(cashReceiptEntryService.getCashReceiptEntries(request));
        cashReceipt.setCard(discountCartService.getDiscountCardByNumber(request.getIdCard()));
        cashReceiptCalculationStrategy.calculate(cashReceipt);

        return cashReceipt;
    }
}
