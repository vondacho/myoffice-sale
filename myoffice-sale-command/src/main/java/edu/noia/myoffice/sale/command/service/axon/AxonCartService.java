package edu.noia.myoffice.sale.command.service.axon;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.sale.domain.command.cart.CreateCartCommand;
import edu.noia.myoffice.sale.domain.repository.CartRepository;
import edu.noia.myoffice.sale.domain.service.CartService;
import edu.noia.myoffice.sale.domain.vo.CartSample;

public class AxonCartService extends CartService {

    public AxonCartService(CartRepository cartRepository, EventPublisher eventPublisher) {
        super(cartRepository, eventPublisher);
    }

    @Override
    public void create(CreateCartCommand command) {
        cartRepository.save(null, CartSample.from(command.getSpecification()));
    }
}
