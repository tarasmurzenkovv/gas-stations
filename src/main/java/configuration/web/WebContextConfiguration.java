package configuration.web;

import configuration.database.DeployDataSourceConfiguration;
import configuration.database.DevelopmentDataSourceConfiguration;
import configuration.repository.RepositoryContextConfiguration;
import configuration.security.PasswordEncoderBeanDeclaration;
import configuration.security.SecurityApplicationContextConfiguration;
import configuration.swagger.SwaggerConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@Import({
        DeployDataSourceConfiguration.class,
        DevelopmentDataSourceConfiguration.class,
        RepositoryContextConfiguration.class,
        SecurityApplicationContextConfiguration.class,
        PasswordEncoderBeanDeclaration.class,
        SwaggerConfiguration.class})
@ComponentScan(basePackages = {"service", "controllers"})
public class WebContextConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/*").addResourceLocations("/resources/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
