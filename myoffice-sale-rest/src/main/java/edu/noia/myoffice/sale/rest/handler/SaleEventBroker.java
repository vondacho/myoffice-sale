package edu.noia.myoffice.sale.rest.handler;

import edu.noia.myoffice.common.util.broker.DefaultBroker;
import edu.noia.myoffice.sale.domain.event.cart.CartEventPayload;
import edu.noia.myoffice.sale.domain.event.cart.CartNotFoundEventPayload;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.rest.event.SaleEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

import javax.annotation.PreDestroy;
import java.time.Instant;
import java.util.UUID;

import static edu.noia.myoffice.sale.rest.processor.Processors.addHateoas;
import static edu.noia.myoffice.sale.rest.processor.Processors.toSaleEvent;

@Slf4j
public class SaleEventBroker extends DefaultBroker<SaleEvent, UUID> {

    @Autowired
    ResourceProcessor<Resource<CartId>> hateoasProcessor;

    public void onSuccess(CartEventPayload event, Instant timestamp) {
        toSaleEvent(timestamp)
                .apply(event)
                .flatMap(addHateoas(hateoasProcessor)).ifPresent(this::accept);
    }

    public void onFailure(CartNotFoundEventPayload event, Instant timestamp) {
        toSaleEvent(timestamp).apply(event).ifPresent(this::accept);
    }

    @PreDestroy
    @Override
    public void complete() {
        super.complete();
        LOG.debug("broker has been completed");
    }
}
