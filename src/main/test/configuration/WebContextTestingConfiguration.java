package configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import service.CustomerService;
import service.FuelingService;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"controllers"})
public class WebContextTestingConfiguration {
    @Bean
    FuelingService fuelingService() {
        return Mockito.mock(FuelingService.class);
    }

    @Bean
    CustomerService customerService() {
        return Mockito.mock(CustomerService.class);
    }
}
