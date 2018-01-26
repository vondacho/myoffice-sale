package edu.noia.myoffice.sale.command.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.noia.myoffice.sale.command.axon.aggregate.AxonESCart;
import edu.noia.myoffice.sale.command.axon.repository.AxonESRepository;
import edu.noia.myoffice.sale.domain.repository.command.CartRepository;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Autowired
    private AxonConfiguration axonConfiguration;

    @Bean
    public CartRepository cartRepository() {
        return new AxonESRepository(axonConfiguration.repository(AxonESCart.class));
    }

    @Bean
    public Serializer serializer() {
        ObjectMapper mapper = new ObjectMapper();
        return new JacksonSerializer(mapper);
    }

    /*
    @Bean
    public EventBus eventBus() {
        return new SimpleEventBus();
    }

    @Bean
    public CommandBus commandBus() {
        return new SimpleCommandBus();
    }

    @Bean
    public CommandGateway commandGateway(CommandBus commandBus) {
        return new DefaultCommandGateway(commandBus);
    }

    @Bean
    public SagaConfiguration cartSagaConfiguration() {
        return SagaConfiguration.trackingSagaManager(ItemAdditionSagaImpl.class);
    }

    @Primary
    @Autowired
    public void configure(@Qualifier("localSegment") SimpleCommandBus simpleCommandBus) {
        simpleCommandBus.registerDispatchInterceptor(new BeanValidationInterceptor<>());
    }
    */
}
