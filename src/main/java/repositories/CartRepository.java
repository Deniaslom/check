package repositories;

import model.DiscountCard;

import java.util.Map;

public interface CartRepository {

    Map<Integer, DiscountCard> getCarts();

    DiscountCard getCartById(int id);

    boolean add(DiscountCard cart);

    void deleteById(int id);
}
