package edu.noia.myoffice.sale.query;

import edu.noia.myoffice.sale.query.event.axon.AxonCartEventHandler;
import edu.noia.myoffice.sale.query.repository.CartStateRepository;
import edu.noia.myoffice.sale.query.service.CartUpdater;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaleQueryComponentConfiguration {

    @Bean
    public AxonCartEventHandler cartUpdater(CartStateRepository cartStateRepository) {
        return new AxonCartEventHandler(new CartUpdater(cartStateRepository));
    }
}
