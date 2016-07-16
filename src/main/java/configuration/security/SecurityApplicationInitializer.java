package configuration.security;

import org.springframework.context.annotation.Profile;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Profile("prod")
public class SecurityApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
