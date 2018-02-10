package edu.noia.myoffice.sale.ui;

import edu.noia.myoffice.sale.command.SaleCommandComponentConfig;
import edu.noia.myoffice.sale.command.data.SaleCommandDataComponentConfig;
import edu.noia.myoffice.sale.common.processor.EventProcessor;
import edu.noia.myoffice.sale.domain.event.cart.CartEvent;
import edu.noia.myoffice.sale.messaging.SaleMessagingComponentConfig;
import edu.noia.myoffice.sale.query.SaleQueryComponentConfig;
import edu.noia.myoffice.sale.query.data.SaleQueryDataComponentConfig;
import edu.noia.myoffice.sale.query.data.hateoas.CartIdResourceProcessor;
import edu.noia.myoffice.sale.rest.SaleRestApplicationConfig;
import edu.noia.myoffice.sale.ui.event.SaleEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static edu.noia.myoffice.sale.ui.processor.Processors.addHateoas;
import static edu.noia.myoffice.sale.ui.processor.Processors.toSaleEvent;

@Import(value = {
        SaleCommandComponentConfig.class,
        SaleCommandDataComponentConfig.class,
        SaleQueryComponentConfig.class,
        SaleQueryDataComponentConfig.class,
        SaleMessagingComponentConfig.class,
        SaleRestApplicationConfig.class
})
@ComponentScan
@Configuration
public class SaleApplicationConfig {

    @Bean
    public EventProcessor<CartEvent, SaleEvent> eventProcessor(CartIdResourceProcessor hateoasProcessor) {
        return event -> addHateoas.apply(toSaleEvent.apply(event), hateoasProcessor);
    }
}
