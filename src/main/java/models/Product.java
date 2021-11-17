package models;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private BigDecimal price;
    private boolean isDiscount;

}
