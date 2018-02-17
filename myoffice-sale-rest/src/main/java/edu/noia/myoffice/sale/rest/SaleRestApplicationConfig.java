package edu.noia.myoffice.sale.rest;

import edu.noia.myoffice.common.serializer.CommonSerializers;
import edu.noia.myoffice.sale.common.mixin.CartItemMixin;
import edu.noia.myoffice.sale.common.serializer.SaleSerializers;
import edu.noia.myoffice.sale.domain.vo.CartItem;
import edu.noia.myoffice.sale.rest.handler.axon.AxonSaleEventBrokerProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@ComponentScan
@Configuration
public class SaleRestApplicationConfig {

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .mixIn(CartItem.class, CartItemMixin.class)
                .modules(CommonSerializers.getModule(), SaleSerializers.getModule());
    }

    @Bean
    public AxonSaleEventBrokerProxy saleEventSource() {
        return new AxonSaleEventBrokerProxy();
    }
}
