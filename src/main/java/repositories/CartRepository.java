package repositories;

import model.DiscountCard;

public interface CartRepository {

    DiscountCard getCartById(int id);

    DiscountCard save(DiscountCard cart);

    void delete(int id);

    void update(int id, DiscountCard cart);
}
