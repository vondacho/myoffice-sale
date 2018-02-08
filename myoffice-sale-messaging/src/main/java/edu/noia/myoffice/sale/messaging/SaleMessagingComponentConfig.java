package edu.noia.myoffice.sale.messaging;

import edu.noia.myoffice.common.domain.command.CommandPublisher;
import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.sale.messaging.adapter.CommandPublisherAdapter;
import edu.noia.myoffice.sale.messaging.adapter.EventPublisherAdapter;
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
    public CommandPublisher commandPublisher(CommandGateway commandGateway) {
        return new CommandPublisherAdapter(commandGateway);
    }
}
