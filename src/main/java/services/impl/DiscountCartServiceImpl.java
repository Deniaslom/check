package services.impl;

import model.DiscountCard;
import repositories.CartRepository;
import repositories.impl.CartRepositoryImpl;
import services.DiscountCartService;

public class DiscountCartServiceImpl implements DiscountCartService {
    CartRepository cartRepository = new CartRepositoryImpl();

    public DiscountCard getDiscountCardByNumber(Integer number) {
        return cartRepository.getCartById(number);
    }

    @Override
    public DiscountCard save(DiscountCard card) {
        return cartRepository.save(card);
    }

    @Override
    public void delete(int id) {
        cartRepository.delete(id);
    }

    @Override
    public void update(int id, DiscountCard card) {
        cartRepository.update(id, card);
    }
}
