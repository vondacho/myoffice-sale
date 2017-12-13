package edu.noia.myoffice.sale.data.adapter;

import edu.noia.myoffice.sale.data.jpa.JpaCartState;
import edu.noia.myoffice.sale.data.jpa.JpaCartStateRepository;
import edu.noia.myoffice.sale.domain.aggregate.Cart;
import edu.noia.myoffice.sale.domain.aggregate.CartState;
import edu.noia.myoffice.sale.domain.repository.CartRepository;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.FolderId;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartRepositoryAdapter implements CartRepository {

    @NonNull
    JpaCartStateRepository repository;

    @Override
    public Optional<Cart> findOne(CartId id) {
        return repository
                .findById(id.getId())
                .map(this::toCart);
    }

    @Override
    public Page<Cart> findByFolderId(FolderId folderId, Pageable pageable) {
        return repository
                .findByFolderId(folderId.getId(), pageable)
                .map(this::toCart);
    }

    @Override
    public List<Cart> findAll(Specification specification) {
        return emptyList();
    }

    @Override
    public Page<Cart> findAll(Specification specification, Pageable pageable) {
        return new PageImpl<>(emptyList());
    }

    @Override
    public Cart save(Cart cart) {
        return save(cart.getId(), cart.getState());
    }

    @Override
    public Cart save(CartId id, CartState state) {
        return Cart.ofValid(id, repository.save(toJpaEntity(state).setId(id.getId())));
    }

    @Override
    public void delete(CartId id) {
        repository
                .findById(id.getId())
                .ifPresent(folder -> repository.delete(folder));
    }

    @Override
    public Page<Cart> findAll(Pageable pageable) {
        return new PageImpl<>(emptyList());
    }

    private Cart toCart(JpaCartState state) {
        return Cart.ofValid(CartId.of(state.getId()), state);
    }

    private static JpaCartState toJpaEntity(CartState state) {
        return state instanceof JpaCartState ? (JpaCartState)state : JpaCartState.of(state);
    }
}
