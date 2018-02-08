package edu.noia.myoffice.sale.query.data;

import edu.noia.myoffice.sale.query.data.adapter.CartStateRepositoryAdapter;
import edu.noia.myoffice.sale.query.data.jpa.JpaCartStateRepository;
import edu.noia.myoffice.sale.query.handler.CartUpdater;
import edu.noia.myoffice.sale.query.handler.axon.CartUpdaterAdapter;
import edu.noia.myoffice.sale.query.repository.CartStateRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EntityScan
@Configuration
public class SaleQueryDataComponentConfig {

    @Bean
    public CartStateRepository cartStateRepository(JpaCartStateRepository jpaCartStateRepository) {
        return new CartStateRepositoryAdapter(jpaCartStateRepository);
    }

    @Bean
    public CartUpdater cartUpdater(CartStateRepository cartStateRepository) {
        return new CartUpdaterAdapter(cartStateRepository);
    }
}
