package services.impl;

import model.DiscountCard;
import repositories.CartRepository;
import repositories.impl.CartRepositoryImpl;
import services.DiscountCartService;

import java.util.Optional;

public class DiscountCartServiceImpl implements DiscountCartService {
    CartRepository cartRepository = new CartRepositoryImpl();

    public Optional<DiscountCard> getDiscountCardByNumber(Integer number) {
        return Optional.ofNullable(cartRepository.getCartById(number));
    }
}
