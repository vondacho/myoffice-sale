package edu.noia.myoffice.sale.query;

import edu.noia.myoffice.sale.query.handler.CartUpdater;
import edu.noia.myoffice.sale.query.handler.DefaultCartUpdater;
import edu.noia.myoffice.sale.query.handler.axon.AxonCartUpdater;
import edu.noia.myoffice.sale.query.repository.CartStateRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaleQueryComponentConfiguration {

    @Bean
    public CartUpdater cartUpdater(CartStateRepository cartStateRepository) {
        return new AxonCartUpdater(new DefaultCartUpdater(cartStateRepository));
    }
}
