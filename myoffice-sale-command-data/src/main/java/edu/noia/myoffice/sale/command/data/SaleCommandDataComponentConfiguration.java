package edu.noia.myoffice.sale.command.data;

import edu.noia.myoffice.sale.command.aggregate.axon.AxonCart;
import edu.noia.myoffice.sale.command.data.repository.axon.AxonCartRepository;
import edu.noia.myoffice.sale.domain.repository.CartRepository;
import org.axonframework.eventhandling.saga.repository.SagaStore;
import org.axonframework.eventhandling.saga.repository.inmemory.InMemorySagaStore;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaleCommandDataComponentConfiguration {

    @Autowired
    private AxonConfiguration axonConfiguration;

    @Bean
    public EventStore eventStore() {
        return new EmbeddedEventStore(new InMemoryEventStorageEngine());
    }

    @Bean
    public SagaStore sagaStore() {
        return new InMemorySagaStore();
    }

    @Bean
    public CartRepository cartRepository() {
        return new AxonCartRepository(axonConfiguration.repository(AxonCart.class));
    }
}
