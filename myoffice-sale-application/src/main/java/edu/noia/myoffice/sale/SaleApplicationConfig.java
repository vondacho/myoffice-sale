package edu.noia.myoffice.sale;

import edu.noia.myoffice.sale.command.SaleCommandComponentConfig;
import edu.noia.myoffice.sale.command.data.SaleCommandDataComponentConfig;
import edu.noia.myoffice.sale.messaging.SaleMessagingComponentConfig;
import edu.noia.myoffice.sale.query.SaleQueryComponentConfig;
import edu.noia.myoffice.sale.query.data.SaleQueryDataComponentConfig;
import edu.noia.myoffice.sale.rest.SaleRestComponentConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(value = {
        SaleCommandComponentConfig.class,
        SaleCommandDataComponentConfig.class,
        SaleQueryComponentConfig.class,
        SaleQueryDataComponentConfig.class,
        SaleMessagingComponentConfig.class,
        SaleRestComponentConfig.class
})
@Configuration
public class SaleApplicationConfig {
}
