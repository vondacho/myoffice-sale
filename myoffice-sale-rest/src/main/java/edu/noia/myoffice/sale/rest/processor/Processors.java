package edu.noia.myoffice.sale.rest.processor;

import edu.noia.myoffice.common.util.processor.Filter;
import edu.noia.myoffice.common.util.processor.Processor;
import edu.noia.myoffice.sale.domain.event.cart.CartEventPayload;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.rest.event.SaleEvent;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

import java.time.Instant;
import java.util.Optional;

public class Processors {

    private Processors() {
    }

    public static Processor<CartEventPayload, SaleEvent> toSaleEvent(Instant timestamp) {
        return event -> Optional.of(SaleEvent.of(timestamp, event.getClass(), new Resource<>(event.getCartId())));
    }

    public static Processor<SaleEvent, SaleEvent> addHateoas(ResourceProcessor<Resource<CartId>> processor) {
        return event -> {
            processor.process(event.getPayload());
            return Optional.of(event);
        };
    }

    public static Filter<SaleEvent> forCart(CartId cartId) {
        return event -> Optional.ofNullable(
                event.getPayload().getContent().getId().equals(cartId.getId()) ? event : null);
    }
}
