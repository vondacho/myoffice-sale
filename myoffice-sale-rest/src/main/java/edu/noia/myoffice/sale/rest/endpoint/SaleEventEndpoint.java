package edu.noia.myoffice.sale.rest.endpoint;

import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.rest.event.SaleEvent;
import edu.noia.myoffice.sale.rest.handler.SaleEventBroker;
import edu.noia.myoffice.sale.rest.handler.SaleEventFluxSinkRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static edu.noia.myoffice.sale.rest.processor.Processors.addHateoas;

@RestController
@RequestMapping("/api/sale/v1/events")
public class SaleEventEndpoint {

    @Autowired
    SaleEventBroker saleEventBroker;
    @Autowired
    ResourceProcessor<Resource<CartId>> hateoasProcessor;

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<SaleEvent> saleEvents() {
        return Flux.create(sink -> SaleEventFluxSinkRegistrar.register(sink,
                addHateoas(hateoasProcessor), saleEventBroker));
    }
}
