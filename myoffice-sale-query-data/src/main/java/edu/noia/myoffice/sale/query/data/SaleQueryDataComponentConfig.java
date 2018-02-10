package edu.noia.myoffice.sale.query.data;

import edu.noia.myoffice.sale.query.data.adapter.CartStateRepositoryAdapter;
import edu.noia.myoffice.sale.query.data.hateoas.CartIdResourceProcessor;
import edu.noia.myoffice.sale.query.data.jpa.JpaCartStateRepository;
import edu.noia.myoffice.sale.query.repository.CartStateRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;

@EnableJpaRepositories
@EntityScan
@Configuration
public class SaleQueryDataComponentConfig {

    @Bean
    public CartStateRepository cartStateRepository(JpaCartStateRepository jpaCartStateRepository) {
        return new CartStateRepositoryAdapter(jpaCartStateRepository);
    }

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return new RepositoryRestConfigurerAdapter() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.setBasePath("/api/sale/query/v1");
            }
        };
    }

    @Bean
    public CartIdResourceProcessor cartIdResourceProcessor(RepositoryEntityLinks entityLinks) {
        return new CartIdResourceProcessor(entityLinks);
    }

}
