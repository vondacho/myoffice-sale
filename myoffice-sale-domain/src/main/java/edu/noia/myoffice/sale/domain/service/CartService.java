package edu.noia.myoffice.sale.domain.service;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.common.domain.util.EntityFinder;
import edu.noia.myoffice.sale.domain.aggregate.Cart;
import edu.noia.myoffice.sale.domain.repository.CartRepository;
import edu.noia.myoffice.sale.domain.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    private CartRepository repository;
    @Autowired
    private EventPublisher eventPublisher;

    @Transactional
    public InvoiceId invoice(FolderId id) {
        return repository.findOne(id, CartType.LOG)
                .map(cart -> invoice(cart.getId()))
                .orElse(null);
    }

    @Transactional
    public InvoiceId invoice(CartId id) {
        return invoice(EntityFinder.find(Cart.class, id, repository::findOne));
    }

    private InvoiceId invoice(Cart cart) {
        InvoiceId invoiceId = InvoiceId.of(UUID.randomUUID());
        repository.save(cart.invoice(invoiceId));
        eventPublisher.publish(CreateInvoiceEvent.of(cart.getId(), invoiceId));
        return invoiceId;
    }
}
