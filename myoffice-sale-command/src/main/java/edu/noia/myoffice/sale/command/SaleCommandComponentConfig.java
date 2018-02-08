package edu.noia.myoffice.sale.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.common.serializer.CommonSerializers;
import edu.noia.myoffice.sale.command.handler.CartServiceAdapter;
import edu.noia.myoffice.sale.command.handler.InventoryCommandHandler;
import edu.noia.myoffice.sale.common.mixin.CartItemMixin;
import edu.noia.myoffice.sale.common.serializer.SaleSerializers;
import edu.noia.myoffice.sale.domain.repository.CartRepository;
import edu.noia.myoffice.sale.domain.vo.CartItem;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class SaleCommandComponentConfig {

    @Bean
    public CartServiceAdapter cartServiceAdapter(CartRepository cartRepository, EventPublisher eventPublisher) {
        return new CartServiceAdapter(cartRepository, eventPublisher);
    }

    @Bean
    public InventoryCommandHandler inventoryCommandHandler(EventPublisher eventPublisher) {
        return new InventoryCommandHandler(eventPublisher);
    }

    @Bean
    public Serializer serializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(CommonSerializers.getModule());
        objectMapper.registerModule(SaleSerializers.getModule());
        objectMapper.addMixIn(CartItem.class, CartItemMixin.class);
        return new JacksonSerializer(objectMapper);
    }
}
