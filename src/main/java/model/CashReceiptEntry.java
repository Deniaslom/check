package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CashReceiptEntry {

    private int quantity;
    private Product product;
    private BigDecimal discount;
    private BigDecimal totalPrice;
}
