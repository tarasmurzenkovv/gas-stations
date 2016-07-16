package configuration.repository;

import configuration.database.DataBaseContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DataBaseContextConfiguration.class)
@ComponentScan(basePackages = {"repository"})
public class RepositoryContextConfiguration {

    private static final Integer NUMBER_OF_ROWS_PER_PAGE = 2;

    @Bean
    public Integer numberOfRowsPerPage() {
        return NUMBER_OF_ROWS_PER_PAGE;
    }

}
