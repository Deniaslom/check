package services;

import model.DiscountCard;
import model.Product;

import java.util.Optional;

public interface DiscountCartService {

    DiscountCard getDiscountCardByNumber(Integer number);

    DiscountCard save(DiscountCard card);

    void delete(int id);

    void update(int id, DiscountCard card);
}
