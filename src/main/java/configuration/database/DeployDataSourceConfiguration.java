package configuration.database;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Profile("deploy")
@Configuration
@PropertySource("classpath:application.properties")
public class DeployDataSourceConfiguration {
    private final static int EXIT_STATUS_CODE = 666;
    private static final Logger logger = Logger.getLogger(DeployDataSourceConfiguration.class);

    @Bean
    public DataSource getDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(getUriConnectionParametersForHerokuDataBase().get("uri"));
        basicDataSource.setUsername(getUriConnectionParametersForHerokuDataBase().get("login"));
        basicDataSource.setPassword(getUriConnectionParametersForHerokuDataBase().get("password"));
        return basicDataSource;
    }

    private Map<String, String> getUriConnectionParametersForHerokuDataBase() {
        Map<String, String> credentialForHerokuDataBase = new HashMap<>();
        try {
            String database_url = System.getenv("DATABASE_URL");
            logger.info("Got the following URI: " + database_url);
            URI dbUri = new URI(database_url);
            credentialForHerokuDataBase.put("login", dbUri.getUserInfo().split(":")[0]);
            credentialForHerokuDataBase.put("password", dbUri.getUserInfo().split(":")[1]);
            credentialForHerokuDataBase.put("uri", formStringUri(dbUri));

        } catch (URISyntaxException e) {
            logger.error("Unable to parse given URI ");
            logger.error(e.getMessage(), e);
            System.exit(EXIT_STATUS_CODE);
        }
        logger.info("Info about parsed credentials: "
                + "\n URI: " + credentialForHerokuDataBase.get("uri")
                + "\n Login :" + credentialForHerokuDataBase.get("login")
                + "\n Password: ******* \n"
        );
        return credentialForHerokuDataBase;
    }

    private String formStringUri(URI dbUri) {
        return "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
    }
}
