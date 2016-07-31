package configuration.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Properties;

@Configuration
class AdditionalPropertiesConfiguration {
    @Bean
    @Profile("deploy")
    @Qualifier("additionalProperties")
    Properties getPostgreHibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return properties;
    }

    @Bean
    @Profile("dev")
    @Qualifier("additionalProperties")
    Properties getMySqlHibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        return properties;
    }
}
