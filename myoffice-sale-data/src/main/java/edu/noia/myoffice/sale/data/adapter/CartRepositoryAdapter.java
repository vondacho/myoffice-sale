package edu.noia.myoffice.sale.data.adapter;

import edu.noia.myoffice.sale.data.jpa.JpaCartState;
import edu.noia.myoffice.sale.data.jpa.JpaCartStateRepository;
import edu.noia.myoffice.sale.domain.aggregate.CartState;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.CartSample;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartRepositoryAdapter {

    @NonNull
    JpaCartStateRepository repository;

    public Optional<CartState> findOne(CartId id) {
        return repository
                .findById(id)
                .map(this::toCart);
    }

    public List<CartState> findAll(Specification specification) {
        return repository
                .findAll(specification)
                .stream()
                .map(this::toCart)
                .collect(toList());
    }

    public Page<CartState> findAll(Specification specification, Pageable pageable) {
        return repository
                .findAll(specification, pageable)
                .map(this::toCart);
    }

    public Page<CartState> findAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(this::toCart);
    }

    public CartState save(CartState state) {
        return CartSample.of(repository.save(toJpaEntity(state)));
    }

    public void delete(CartId id) {
        repository.findById(id).ifPresent(cart -> repository.delete(cart));
    }

    //@Override
    public List<CartState> findAllByFolderId(CartId id) {
        return repository.findByFolderId(id).stream().map(this::toCart).collect(toList());
    }

    private CartState toCart(JpaCartState state) {
        return state;
    }

    private JpaCartState toJpaEntity(CartState state) {
        return state instanceof JpaCartState ? (JpaCartState)state : JpaCartState.of(state);
    }
}
