package serializations;

import model.CashReceipt;

public interface CashReceiptSerializator {
    String deserialize(CashReceipt cashReceipt);
}
