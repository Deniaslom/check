package services;

import model.DiscountCard;

import java.util.Optional;

public interface DiscountCartService {

    Optional<DiscountCard> getDiscountCardByNumber(Integer number);
}
