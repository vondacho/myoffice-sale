package edu.noia.myoffice.sale.config;

import edu.noia.myoffice.sale.command.SaleCommandComponentConfiguration;
import edu.noia.myoffice.sale.command.data.SaleCommandDataComponentConfiguration;
import edu.noia.myoffice.sale.messaging.SaleMessagingComponentConfiguration;
import edu.noia.myoffice.sale.query.SaleQueryComponentConfiguration;
import edu.noia.myoffice.sale.query.data.SaleQueryDataComponentConfiguration;
import edu.noia.myoffice.sale.rest.SaleRestComponentConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(value = {
        SaleCommandComponentConfiguration.class,
        SaleCommandDataComponentConfiguration.class,
        SaleQueryComponentConfiguration.class,
        SaleQueryDataComponentConfiguration.class,
        SaleMessagingComponentConfiguration.class,
        SaleRestComponentConfiguration.class
})
@Configuration
public class SaleApplicationConfiguration {
}
