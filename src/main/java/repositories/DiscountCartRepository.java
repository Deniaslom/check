package repositories;

import beans.DiscountCard;

import java.util.Collection;
import java.util.Optional;

public interface DiscountCartRepository {
    Optional<DiscountCard> getDiscountCardByNumber(Integer number);
    Collection<DiscountCard> getDiscountCards();
}
