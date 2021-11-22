package services.impl;

import models.CashReceipt;
import models.CashReceiptRequest;
import models.DiscountCard;
import repositories.DiscountCartRepository;
import services.CashReceiptEntryService;
import services.CashReceiptService;
import services.straregies.CashReceiptCalculationStrategy;

import java.time.LocalDateTime;


public class CashReceiptServiceImpl implements CashReceiptService {

    private CashReceiptEntryService cashReceiptEntryService;
    private CashReceiptCalculationStrategy cashReceiptCalculationStrategy;
    private DiscountCartRepository cartRepository;


    public CashReceiptServiceImpl(CashReceiptEntryService cashReceiptEntryService, CashReceiptCalculationStrategy cashReceiptCalculationStrategy, DiscountCartRepository cartRepository) {
        this.cashReceiptEntryService = cashReceiptEntryService;
        this.cashReceiptCalculationStrategy = cashReceiptCalculationStrategy;
        this.cartRepository = cartRepository;
    }

    @Override
    public CashReceipt getCashReceipt(CashReceiptRequest request) {
        CashReceipt cashReceipt = new CashReceipt();

        cashReceipt.setCreationTime(LocalDateTime.now());
        cashReceipt.setEntries(cashReceiptEntryService.getCashReceiptEntries(request));
        cashReceipt.setCard(cartRepository.getDiscountCardByNumber(request.getIdCard()).orElse(new DiscountCard(null, 0)));
        cashReceiptCalculationStrategy.calculate(cashReceipt);

        return cashReceipt;
    }
}
