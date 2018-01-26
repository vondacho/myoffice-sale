package edu.noia.myoffice.sale.data;

import edu.noia.myoffice.sale.data.jpa.JpaCartStateRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyOfficeSaleDataApplication.class})
public class MyOfficeSaleDataApplicationIT {

    @Autowired
    private JpaCartStateRepository cartStateRepository;

    @Test
    public void contextLoads() {
        Assertions.assertThat(cartStateRepository).isNotNull();
    }
}
