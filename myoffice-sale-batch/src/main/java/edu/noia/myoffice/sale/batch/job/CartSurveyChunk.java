package edu.noia.myoffice.sale.batch.job;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.sale.domain.aggregate.CartState;
import edu.noia.myoffice.sale.domain.event.cart.CartDeclaredAsRequestableEvent;
import edu.noia.myoffice.sale.query.data.adapter.CartStateRepositoryAdapter;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class CartSurveyChunk {

    @NonNull
    CartStateRepositoryAdapter cartRepository;
    @NonNull
    EventPublisher eventPublisher;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Optional<Pageable> execute(Pageable pageable) {
/*        Page<CartState> page = cartRepository.findAll();
        page.getContent().forEach(this::checkRequestableCart);*/
        return Optional.empty(); // page.isLast() ? Optional.empty() : Optional.of(pageable.next());
    }

    private void checkRequestableCart(CartState state) {
        eventPublisher.accept(CartDeclaredAsRequestableEvent.of(null));
    }
}
