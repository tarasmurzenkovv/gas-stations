package configuration.web;

import configuration.repository.RepositoryContextConfiguration;
import configuration.security.PasswordEncoderBeanDeclaration;
import configuration.security.SecurityApplicationContextConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@Import({RepositoryContextConfiguration.class,
        SecurityApplicationContextConfiguration.class,
        PasswordEncoderBeanDeclaration.class})
@ComponentScan(basePackages = {"service", "controllers"})
public class WebContextConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/*").addResourceLocations("/resources/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}