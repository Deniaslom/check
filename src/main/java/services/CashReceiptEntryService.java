package services;

import models.CashReceiptEntry;
import models.CashReceiptRequest;

import java.util.List;

public interface CashReceiptEntryService {

    List<CashReceiptEntry> getCashReceiptEntries(CashReceiptRequest request);
}
