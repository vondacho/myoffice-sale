package edu.noia.myoffice.sale.command;

import edu.noia.myoffice.common.rest.MyOfficeCommonRestApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({
        MyOfficeCommonRestApplication.class
})
@SpringBootApplication
public class MyOfficeSaleCommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyOfficeSaleCommandApplication.class, args);
    }
}
