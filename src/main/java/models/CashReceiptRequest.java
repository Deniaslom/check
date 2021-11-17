package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class CashReceiptRequest {
    private Map<Integer, Integer> productsWithQuantity;
    private Integer idCard;

}
