package edu.noia.myoffice.sale.command;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.common.domain.vo.Quantity;
import edu.noia.myoffice.common.domain.vo.Rate;
import edu.noia.myoffice.common.mixin.QuantityMixin;
import edu.noia.myoffice.common.mixin.RateMixin;
import edu.noia.myoffice.common.serializer.CommonSerializers;
import edu.noia.myoffice.sale.command.handler.axon.AxonCartServiceProxy;
import edu.noia.myoffice.sale.command.handler.axon.AxonInventoryCommandHandler;
import edu.noia.myoffice.sale.common.mixin.CartItemMixin;
import edu.noia.myoffice.sale.common.serializer.SaleSerializers;
import edu.noia.myoffice.sale.domain.repository.CartRepository;
import edu.noia.myoffice.sale.domain.vo.CartItem;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@ComponentScan
@Configuration
public class SaleCommandComponentConfig {

    @Bean
    public AxonCartServiceProxy cartCommandHandler(CartRepository cartRepository, EventPublisher eventPublisher) {
        return new AxonCartServiceProxy(cartRepository, eventPublisher);
    }

    @Bean
    public AxonInventoryCommandHandler inventoryCommandHandler(EventPublisher eventPublisher) {
        return new AxonInventoryCommandHandler(eventPublisher);
    }

    @Primary
    @Bean
    public Serializer serializer() {
        return new JacksonSerializer(
                new ObjectMapper()
                        .registerModule(CommonSerializers.getModule())
                        .registerModule(SaleSerializers.getModule())
                        .addMixIn(CartItem.class, CartItemMixin.class)
                        .addMixIn(Quantity.class, QuantityMixin.class)
                        .addMixIn(Rate.class, RateMixin.class)
                        .setSerializationInclusion(JsonInclude.Include.NON_EMPTY));
    }
}
