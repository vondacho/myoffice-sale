package edu.noia.myoffice.sale.query;

import edu.noia.myoffice.sale.data.MyOfficeSaleDataApplication;
import edu.noia.myoffice.sale.query.handler.CartUpdater;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        MyOfficeSaleDataApplication.class,
        MyOfficeSaleQueryApplication.class
})
public class MyOfficeSaleQueryApplicationIT {

    @Autowired
    private CartUpdater cartUpdater;

    @Test
    public void contextLoads() {
        Assertions.assertThat(cartUpdater).isNotNull();
    }
}
