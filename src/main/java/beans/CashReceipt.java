package beans;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CashReceipt {
    private List<CashReceiptEntry> entries;
    private LocalDateTime creationTime;   // изменить на локалДате
    private BigDecimal discount;
    private BigDecimal totalPrice;
}
