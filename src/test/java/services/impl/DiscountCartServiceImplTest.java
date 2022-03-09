package services.impl;

import model.DiscountCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.DiscountCartService;

class DiscountCartServiceImplTest {
    private DiscountCartService cartService = new DiscountCartServiceImpl();
    private static DiscountCard card = null;
    private final static DiscountCard CART_OPTIONAL = new DiscountCard();

    @BeforeEach
    public void before() {
        card = new DiscountCard(null, 123);
    }

    @Test
    public void findCartIdAfterAdd() {
        DiscountCard cardFromDb = cartService.save(card);
        System.out.println(cardFromDb);
        System.out.println(cartService.getDiscountCardByNumber(card.getNumber()));
        assert (cardFromDb.equals(cartService.getDiscountCardByNumber(card.getNumber())));
    }

    @Test
    public void updateCartAfterAdd() {
        DiscountCard cardFromDb = cartService.save(card);
        cardFromDb.setDiscount(321);
        cartService.update(cardFromDb.getNumber(), cardFromDb);
        assert (cardFromDb.equals(cartService.getDiscountCardByNumber(cardFromDb.getNumber())));
    }

    @Test
    public void deleteCartAfterAdd() {
        DiscountCard cardFromDb = cartService.save(card);
        cartService.delete(cardFromDb.getNumber());
        DiscountCard cardDb = cartService.getDiscountCardByNumber(cardFromDb.getNumber());
        assert (CART_OPTIONAL.equals(cardDb));
    }

}