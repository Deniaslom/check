package serializations;

import models.CashReceipt;

public interface CashReceiptSerialization {
    String deserialize(CashReceipt cashReceipt);
}
