package edu.noia.myoffice.sale.ui.listener;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.sale.common.listener.CartEventListener;
import edu.noia.myoffice.sale.common.processor.EventProcessor;
import edu.noia.myoffice.sale.domain.event.cart.CartCreatedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartInvoicedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEvent;
import edu.noia.myoffice.sale.ui.event.SaleEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PreDestroy;
import java.util.function.Consumer;

@Slf4j
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaleEventFlux implements CartEventListener {

    FluxSinkAdapter sinkAdapter;

    @Autowired
    EventProcessor<CartEvent, SaleEvent> eventProcessor;

    @Getter
    Flux<Event> eventStream = Flux.create(sink -> sinkAdapter = new FluxSinkAdapter() {
        @Override
        public void accept(Event event) {
            sink.next(event);
        }

        @Override
        public void complete() {
            sink.complete();
        }
    });

    private interface FluxSinkAdapter extends Consumer<Event> {
        void complete();
    }

    private void publish(CartEvent event) {
        if (sinkAdapter != null) {
            sinkAdapter.accept(process(event));
        }
    }

    private Event process(CartEvent event) {
        return eventProcessor.apply(event);
    }

    @PreDestroy
    public void terminate() {
        if (sinkAdapter != null) {
            LOG.debug("{} is being terminated", getClass().getName());
            sinkAdapter.complete();
            LOG.info("{} has been terminated", getClass().getName());
        }
    }

    @EventHandler(payloadType = CartCreatedEvent.class)
    @Override
    public void created(CartCreatedEvent event) {
        publish(event);
    }

    @EventHandler(payloadType = ItemAddedToCartEvent.class)
    @Override
    public void itemAdded(ItemAddedToCartEvent event) {
        publish(event);
    }

    @EventHandler(payloadType = ItemRemovedFromCartEvent.class)
    @Override
    public void itemRemoved(ItemRemovedFromCartEvent event) {
        publish(event);
    }

    @EventHandler(payloadType = CartOrderedEvent.class)
    @Override
    public void ordered(CartOrderedEvent event) {
        publish(event);
    }

    @EventHandler(payloadType = CartInvoicedEvent.class)
    @Override
    public void invoiced(CartInvoicedEvent event) {
        publish(event);
    }

}
