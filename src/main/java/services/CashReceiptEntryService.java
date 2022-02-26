package services;

import model.CashReceiptEntry;
import model.CashReceiptRequest;

import java.util.List;

public interface CashReceiptEntryService {

    List<CashReceiptEntry> getCashReceiptEntries(CashReceiptRequest request);
}
