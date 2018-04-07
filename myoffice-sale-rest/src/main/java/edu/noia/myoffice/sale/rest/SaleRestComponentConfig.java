package edu.noia.myoffice.sale.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.noia.myoffice.common.domain.vo.Quantity;
import edu.noia.myoffice.common.domain.vo.Rate;
import edu.noia.myoffice.common.mixin.QuantityMixin;
import edu.noia.myoffice.common.mixin.RateMixin;
import edu.noia.myoffice.common.rest.exception.EndpointExceptionHandler;
import edu.noia.myoffice.common.serializer.CommonSerializers;
import edu.noia.myoffice.sale.common.mixin.CartItemMixin;
import edu.noia.myoffice.sale.common.serializer.SaleSerializers;
import edu.noia.myoffice.sale.domain.vo.CartItem;
import edu.noia.myoffice.sale.rest.handler.axon.AxonSaleEventBrokerProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@ComponentScan(basePackageClasses = {
        SaleRestComponentConfig.class,
        EndpointExceptionHandler.class
})
@Configuration
public class SaleRestComponentConfig {

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .serializationInclusion(JsonInclude.Include.NON_EMPTY)
                .mixIn(CartItem.class, CartItemMixin.class)
                .mixIn(Quantity.class, QuantityMixin.class)
                .mixIn(Rate.class, RateMixin.class)
                .modules(CommonSerializers.getModule(), SaleSerializers.getModule());
    }

    @Bean
    public AxonSaleEventBrokerProxy saleEventSource() {
        return new AxonSaleEventBrokerProxy();
    }
}
