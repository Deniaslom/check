package serialization;

import model.CashReceipt;

public interface CashReceiptSerializator {
    String deserialize(CashReceipt cashReceipt);
}
