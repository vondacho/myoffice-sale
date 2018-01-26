package edu.noia.myoffice.sale.ui.handler.axon;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.sale.ui.handler.SaleEventHandler;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
public class SaleEventHandlerImpl extends SaleEventHandler {

    @EventHandler
    public void on(Event event) {
        super.on(event);
    }

    @PreDestroy
    public void terminate() {
        super.terminate();
    }

}
