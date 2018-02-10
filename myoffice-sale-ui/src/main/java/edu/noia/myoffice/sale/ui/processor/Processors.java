package edu.noia.myoffice.sale.ui.processor;

import edu.noia.myoffice.sale.domain.event.cart.CartEvent;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.ui.event.SaleEvent;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

import java.time.LocalDateTime;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Processors {

    private Processors() {}

    public static final Function<CartEvent, SaleEvent> toSaleEvent =
            event -> SaleEvent.of(
                    LocalDateTime.now(),
                    event.getClass(),
                    new Resource<>(event.getCartId()));

    public static final BiFunction<SaleEvent, ResourceProcessor<Resource<CartId>>, SaleEvent> addHateoas =
            (event, processor) -> {
                processor.process(event.getCart());
                return event;
            };
}
