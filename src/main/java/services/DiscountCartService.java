package services;

import models.DiscountCard;

import java.util.Optional;

public interface DiscountCartService {

    public Optional<DiscountCard> getDiscountCardByNumber(Integer number);
}
