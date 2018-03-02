package edu.noia.myoffice.sale.command.data.repository.axon;

import edu.noia.myoffice.common.util.holder.Holder;
import edu.noia.myoffice.sale.command.aggregate.axon.AxonCart;
import edu.noia.myoffice.sale.domain.aggregate.Cart;
import edu.noia.myoffice.sale.domain.aggregate.CartState;
import edu.noia.myoffice.sale.domain.repository.CartRepository;
import edu.noia.myoffice.sale.domain.vo.CartId;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.axonframework.commandhandling.model.Repository;

import java.util.Optional;
import java.util.function.Consumer;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AxonCartRepository implements CartRepository {

    @NonNull
    Repository<AxonCart> repository;

    @Override
    public Optional<Holder<Cart>> findOne(CartId cartId) {
        try {
            return Optional.of(new CartHolder(repository.load(cartId.toString())));
        } catch (AggregateNotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public Holder<Cart> save(CartId id, CartState state) {
        try {
            return new CartHolder(repository.newInstance(() -> AxonCart.of(state)));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @RequiredArgsConstructor
    private class CartHolder implements Holder<Cart> {
        @NonNull
        Aggregate<AxonCart> aggregate;

        @Override
        public void execute(Consumer<Cart> action) {
            aggregate.execute(action::accept);
        }
    }
}
