package services.impl;

import models.CashReceipt;
import models.CashReceiptRequest;
import org.apache.log4j.Logger;
import repositories.DiscountCartRepository;
import services.CashReceiptEntryService;
import services.CashReceiptService;
import services.straregies.CashReceiptCalculationStrategy;

import java.time.LocalDateTime;


public class CashReceiptServiceImpl implements CashReceiptService {
    private static final Logger LOGGER = Logger.getLogger(CashReceiptServiceImpl.class);
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
        cashReceipt.setCard(cartRepository.getDiscountCardByNumber(request.getIdCard()).get());   //Throw
        cashReceiptCalculationStrategy.calculate(cashReceipt);

        return cashReceipt;
    }
}
