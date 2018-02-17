package edu.noia.myoffice.sale.rest.handler;

import edu.noia.myoffice.common.util.broker.DefaultBroker;
import edu.noia.myoffice.sale.rest.event.SaleEvent;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PreDestroy;
import java.util.UUID;

@Slf4j
public class SaleEventBroker extends DefaultBroker<SaleEvent, UUID> {

    @PreDestroy
    @Override
    public void complete() {
        super.complete();
        LOG.debug("broker has been completed");
    }
}
