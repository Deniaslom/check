package beans;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CashReceipt {
    private LocalDateTime creationTime;
    private List<CashReceiptEntry> entries;
    private BigDecimal totalDiscount;
    private BigDecimal totalPrice;
}
