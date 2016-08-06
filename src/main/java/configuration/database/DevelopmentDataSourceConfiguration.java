package configuration.database;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Profile("dev")
@Configuration
@PropertySource("classpath:application.properties")
public class DevelopmentDataSourceConfiguration {
    private final static int EXIT_STATUS_CODE = 666;
    private static final Logger logger = Logger.getLogger(DevelopmentDataSourceConfiguration.class);
    @Value("${jdbc.driver}")
    private String driverName;
    @Value("${jdbc.uri}")
    private String jdbcUrl;
    @Value("${jdbc.login}")
    private String login;
    @Value("${jdbc.password}")
    private String password;

    @Bean
    public DataSource getDataSource() {
        // declare properties for c3p0 connection pool
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(driverName);
        } catch (PropertyVetoException e) {
            // this exception occurs when a property was provided with an invalid value.
            logger.error("Invalid driver was provided for a connection pool");
            System.exit(EXIT_STATUS_CODE);
        }
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(login);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
