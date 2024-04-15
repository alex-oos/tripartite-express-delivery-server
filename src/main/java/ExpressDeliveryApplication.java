import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(value = "com.volcano")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,DataSourceAutoConfiguration.class})
public class ExpressDeliveryApplication {

    public static void main(String[] args) {

        SpringApplication.run(ExpressDeliveryApplication.class, args);

    }

}
