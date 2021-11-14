package repositories;

import beans.DiscountCard;

import java.util.Optional;

public interface DiscountCartRepository {
    Optional<DiscountCard> getDiscountCardByNumber(Integer number);
}
