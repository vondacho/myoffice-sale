package edu.noia.myoffice.sale.messaging;

import edu.noia.myoffice.common.domain.command.CommandPublisher;
import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.sale.messaging.adapter.axon.CartCommandCallback;
import edu.noia.myoffice.sale.messaging.adapter.axon.CommandPublisherAdapter;
import edu.noia.myoffice.sale.messaging.adapter.axon.EventPublisherAdapter;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaleMessagingComponentConfig {

    @Bean
    public EventPublisher eventPublisher(EventBus eventBus) {
        return new EventPublisherAdapter(eventBus);
    }

    @Bean
    public CommandPublisher commandPublisher(CommandGateway commandGateway, CartCommandCallback cartCommandCallback) {
        return new CommandPublisherAdapter(commandGateway, cartCommandCallback);
    }

    @Bean
    public CartCommandCallback exceptionHandlerCallback(EventPublisher eventPublisher) {
        return new CartCommandCallback(eventPublisher);
    }
}
