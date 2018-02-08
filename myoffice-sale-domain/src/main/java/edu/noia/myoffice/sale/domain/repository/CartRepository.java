package edu.noia.myoffice.sale.domain.repository;

import edu.noia.myoffice.common.util.holder.Holder;
import edu.noia.myoffice.sale.domain.aggregate.Cart;
import edu.noia.myoffice.sale.domain.aggregate.CartState;
import edu.noia.myoffice.sale.domain.vo.CartId;

import java.util.Optional;

public interface CartRepository {

    Optional<Holder<Cart>> findOne(CartId cartId);

    Holder<Cart> save(CartId id, CartState state);
}
