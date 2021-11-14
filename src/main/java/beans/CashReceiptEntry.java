package beans;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CashReceiptEntry {
    private int quantity;
    private String description;
    private BigDecimal price;
    private BigDecimal discount;
    private BigDecimal totalPrice;

}
