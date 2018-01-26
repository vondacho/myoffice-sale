package edu.noia.myoffice.sale.command.axon.repository;

import edu.noia.myoffice.sale.command.axon.aggregate.AxonESCart;
import edu.noia.myoffice.sale.domain.aggregate.Cart;
import edu.noia.myoffice.sale.domain.aggregate.CartState;
import edu.noia.myoffice.sale.domain.repository.command.CartRepository;
import edu.noia.myoffice.sale.domain.util.Holder;
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
public class AxonESRepository implements CartRepository {

    @NonNull
    Repository<AxonESCart> repository;

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
            return new CartHolder(repository.newInstance(() -> AxonESCart.of(state)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T extends Cart> Holder<T> save(Cart cart) {
        return null;
    }

    @RequiredArgsConstructor
    private class CartHolder implements Holder<Cart> {
        @NonNull
        Aggregate<AxonESCart> aggregate;

        @Override
        public void execute(Consumer<Cart> action) {
            aggregate.execute(axonCart -> action.accept(axonCart));
        }
    }
}
