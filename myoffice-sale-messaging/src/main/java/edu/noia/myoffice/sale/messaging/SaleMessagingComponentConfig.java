package edu.noia.myoffice.sale.messaging;

import edu.noia.myoffice.common.domain.command.CommandPublisher;
import edu.noia.myoffice.common.domain.event.EventPayload;
import edu.noia.myoffice.sale.domain.exception.DomainExceptionHandler;
import edu.noia.myoffice.sale.messaging.adapter.axon.CommandPublisherAdapter;
import edu.noia.myoffice.sale.messaging.adapter.axon.EventPublisherAdapter;
import edu.noia.myoffice.sale.messaging.adapter.axon.SaleCommandCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class SaleMessagingComponentConfig {

    @Bean
    public Consumer<EventPayload> eventPublisher(EventBus eventBus) {
        return new EventPublisherAdapter(eventBus);
    }

    @Bean
    public CommandPublisher commandPublisher(CommandGateway commandGateway, SaleCommandCallback commandCallback) {
        return new CommandPublisherAdapter(commandGateway, commandCallback);
    }

    @Bean
    public SaleCommandCallback exceptionHandlerCallback(Consumer<EventPayload> eventPublisher) {
        return new SaleCommandCallback(new DomainExceptionHandler(eventPublisher));
    }
}
