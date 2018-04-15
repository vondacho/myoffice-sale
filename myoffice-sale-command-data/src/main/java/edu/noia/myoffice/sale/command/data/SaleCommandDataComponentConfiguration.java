package edu.noia.myoffice.sale.command.data;

import edu.noia.myoffice.sale.command.aggregate.axon.AxonCart;
import edu.noia.myoffice.sale.command.data.repository.axon.AxonCartRepository;
import edu.noia.myoffice.sale.domain.repository.CartRepository;
import org.axonframework.common.jpa.SimpleEntityManagerProvider;
import org.axonframework.eventhandling.saga.repository.SagaStore;
import org.axonframework.eventhandling.saga.repository.jpa.JpaSagaStore;
import org.axonframework.eventhandling.saga.repository.jpa.SagaEntry;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.jpa.DomainEventEntry;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.upcasting.event.EventUpcaster;
import org.axonframework.spring.config.AxonConfiguration;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.SQLException;

@EntityScan(basePackageClasses = {
        DomainEventEntry.class,
        SagaEntry.class
})
@Configuration
public class SaleCommandDataComponentConfiguration {

    @Autowired
    private AxonConfiguration axonConfiguration;

    @Bean
    public EventStore eventStore(Serializer serializer,
                                 DataSource dataSource,
                                 EntityManager entityManager,
                                 PlatformTransactionManager transactionManager) throws SQLException {
        final EventUpcaster upcasterChain = null;
        return new EmbeddedEventStore(new JpaEventStorageEngine(
                serializer,
                upcasterChain,
                dataSource,
                new SimpleEntityManagerProvider(entityManager),
                new SpringTransactionManager(transactionManager)));
    }

    @Bean
    public SagaStore sagaStore(Serializer serializer, EntityManager entityManager) {
        return new JpaSagaStore(serializer, new SimpleEntityManagerProvider(entityManager));
    }

    @Bean
    public CartRepository cartRepository() {
        return new AxonCartRepository(axonConfiguration.repository(AxonCart.class));
    }
}
