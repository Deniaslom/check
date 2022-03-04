package services.impl;

import model.DiscountCard;
import repositories.impl.CartRepositoryImpl;
import services.DiscountCartService;

import java.util.Optional;

public class DiscountCartServiceImpl implements DiscountCartService {
    CartRepositoryImpl cartRepository = new CartRepositoryImpl();

    public Optional<DiscountCard> getDiscountCardByNumber(Integer number) {
        return Optional.ofNullable(cartRepository.getCarts().get(number));
    }
}
