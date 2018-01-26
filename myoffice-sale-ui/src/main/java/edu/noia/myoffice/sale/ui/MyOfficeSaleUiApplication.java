package edu.noia.myoffice.sale.ui;

import edu.noia.myoffice.sale.command.MyOfficeSaleCommandApplication;
import edu.noia.myoffice.sale.data.MyOfficeSaleDataApplication;
import edu.noia.myoffice.sale.query.MyOfficeSaleQueryApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({
        MyOfficeSaleDataApplication.class,
        MyOfficeSaleCommandApplication.class,
        MyOfficeSaleQueryApplication.class
})
@SpringBootApplication
public class MyOfficeSaleUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyOfficeSaleUiApplication.class, args);
    }
}
