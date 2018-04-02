package edu.noia.myoffice.sale.query;

import edu.noia.myoffice.sale.query.handler.axon.AxonCartUpdater;
import edu.noia.myoffice.sale.query.repository.CartStateRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaleQueryComponentConfig {

    @Bean
    public AxonCartUpdater cartUpdater(CartStateRepository cartStateRepository) {
        return new AxonCartUpdater(cartStateRepository);
    }
}
