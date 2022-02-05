package repositories;

import models.DiscountCard;

import java.util.*;

public class DiscountCartRepository {

    private static final Map<Integer, DiscountCard> DISCOUNT_CARTS;

    static {
        DISCOUNT_CARTS = new HashMap<>();
        DISCOUNT_CARTS.put(1234, new DiscountCard(1234, 12.5));
        DISCOUNT_CARTS.put(1235, new DiscountCard(1235, 13));
        DISCOUNT_CARTS.put(1236, new DiscountCard(1236, 13.2));
        DISCOUNT_CARTS.put(1237, new DiscountCard(1237, 9.5));
        DISCOUNT_CARTS.put(1238, new DiscountCard(1238, 8.5));
        DISCOUNT_CARTS.put(1239, new DiscountCard(1238, 0));
    }

    public static Map<Integer, DiscountCard> getDiscountCarts(){
        return DISCOUNT_CARTS;
    }

}
