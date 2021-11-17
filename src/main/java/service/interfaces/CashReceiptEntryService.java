package service.interfaces;

import models.CashReceiptEntry;
import models.CashReceiptRequest;
import models.Product;

import java.util.List;
import java.util.Map;

public interface CashReceiptEntryService {
    List<CashReceiptEntry> getListCashReceiptEntries(CashReceiptRequest request);
    Map<Product, Integer> getListProductsByCheck(CashReceiptRequest request);
}
