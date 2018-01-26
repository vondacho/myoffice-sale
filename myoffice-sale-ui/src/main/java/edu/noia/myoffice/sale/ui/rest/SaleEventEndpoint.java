package edu.noia.myoffice.sale.ui.rest;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.sale.ui.handler.SaleEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

//@RestController
@RequestMapping("/api/sale/v1/events")
public class SaleEventEndpoint {

    @Autowired
    SaleEventHandler eventHandler;

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Event> saleEvents() {
        return eventHandler.getEventStream();
    }
}
