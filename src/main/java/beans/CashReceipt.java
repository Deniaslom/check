package beans;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CashReceipt {
    private List<CashReceiptEntry> entries;
    private LocalDateTime creationTime;
    private BigDecimal discount;
    private BigDecimal totalPrice;
}
