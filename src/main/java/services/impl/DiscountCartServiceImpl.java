package services.impl;

import model.DiscountCard;
import repositories.DiscountCartRepository;
import services.DiscountCartService;

import java.util.Optional;

public class DiscountCartServiceImpl implements DiscountCartService {

    public Optional<DiscountCard> getDiscountCardByNumber(Integer number) {
        return Optional.ofNullable(DiscountCartRepository.getDiscountCarts().get(number));
    }
}
