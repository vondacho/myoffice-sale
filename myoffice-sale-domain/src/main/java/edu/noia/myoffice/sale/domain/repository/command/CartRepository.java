package edu.noia.myoffice.sale.domain.repository.command;

import edu.noia.myoffice.sale.domain.aggregate.Cart;
import edu.noia.myoffice.sale.domain.aggregate.CartState;
import edu.noia.myoffice.sale.domain.util.Holder;
import edu.noia.myoffice.sale.domain.vo.CartId;

import java.util.Optional;

public interface CartRepository {

    <T extends Cart> Optional<Holder<T>> findOne(CartId cartId);

    <T extends Cart> Holder<T> save(Cart cart);

    <T extends Cart> Holder<T> save(CartId id, CartState state);
}
