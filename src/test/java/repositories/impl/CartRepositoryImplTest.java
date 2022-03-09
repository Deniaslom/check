package repositories.impl;

import model.DiscountCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.CartRepository;

class CartRepositoryImplTest {
    private CartRepository repository = new CartRepositoryImpl();
    private static DiscountCard card = null;
    private final static DiscountCard CART_OPTIONAL = new DiscountCard();

    @BeforeEach
    public void before() {
        card = new DiscountCard(null, 123);
    }

    @Test
    public void findCartIdAfterAdd() {
        DiscountCard cardFromDb = repository.save(card);
        assert (cardFromDb.equals(repository.getCartById(card.getNumber())));
    }

    @Test
    public void updateCartAfterAdd() {
        DiscountCard cardFromDb = repository.save(card);
        cardFromDb.setDiscount(321);
        repository.update(cardFromDb.getNumber(), cardFromDb);
        assert (cardFromDb.equals(repository.getCartById(cardFromDb.getNumber())));
    }

    @Test
    public void deleteCartAfterAdd() {
        DiscountCard cardFromDb = repository.save(card);
        repository.delete(cardFromDb.getNumber());
        DiscountCard cardDb = repository.getCartById(cardFromDb.getNumber());
        assert (CART_OPTIONAL.equals(cardDb));
    }
}