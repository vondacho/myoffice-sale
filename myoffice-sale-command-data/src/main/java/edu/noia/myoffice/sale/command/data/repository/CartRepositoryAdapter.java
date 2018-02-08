package edu.noia.myoffice.sale.command.data.repository;

import edu.noia.myoffice.common.util.holder.Holder;
import edu.noia.myoffice.sale.command.aggregate.CartAdapter;
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
public class CartRepositoryAdapter implements CartRepository {

    @NonNull
    Repository<CartAdapter> repository;

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
            return new CartHolder(repository.newInstance(() -> CartAdapter.of(state)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequiredArgsConstructor
    private class CartHolder implements Holder<Cart> {
        @NonNull
        Aggregate<CartAdapter> aggregate;

        @Override
        public void execute(Consumer<Cart> action) {
            aggregate.execute(cart -> action.accept(cart));
        }
    }
}
