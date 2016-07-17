package configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("configuration.security")
public class SecurityApplicationContextConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImplementation userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AjaxAuthorizationPoint ajaxAuthorizationPoint;

    private final static String DELETE_VEHICLE_BY_ID = "/delete_vehicle\\?id\\=.*\\Z";
    private final static String DELETE_FUELING_BY_ID = "/delete_fueling\\?id\\=.*\\Z";
    private final static String VIEW_FUELINGS = "/view_fuelings\\?page\\=.*\\Z";
    private final static String UPDATE_GASSTATION = "/update\\?id\\=.*\\Z";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.sessionManagement().disable();

        http.authorizeRequests()
                .antMatchers(
                        "/",
                        "/index.html",
                        "/customer_types",
                        "/gender_groups",
                        "/pages/public/**",
                        "/resources/css/**",
                        "/resources/img/**",
                        "/resources/js/**")
                .permitAll();

        http.authorizeRequests().antMatchers("/ajax_login", "logout").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/register").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/customer_types").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/gender_groups").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/ajax_login").permitAll();

        http.authorizeRequests().antMatchers("/api/v2/api-docs").permitAll();
        http.authorizeRequests().antMatchers("/swagger-ui.html").permitAll();
        http.authorizeRequests().antMatchers("/webjars/springfox-swagger-ui/**").permitAll();
        http.authorizeRequests().antMatchers("/configuration/ui").permitAll();
        ///glacial-wave-61982/api/v2/api-docs
        http.authorizeRequests().antMatchers("/glacial-wave-61982/api/v2/api-docs").permitAll();


        // /webjars/springfox-swagger-ui

        http.authorizeRequests()
                .antMatchers(
                        "/add_vehicle",
                        "/view_vehicles",
                        DELETE_VEHICLE_BY_ID,
                        "/add_fueling",
                        "/delete_fueling",
                        "/view_fuelings",
                        DELETE_FUELING_BY_ID,
                        VIEW_FUELINGS)
                .hasAuthority("REGULAR")
                .antMatchers(
                        "/add_gasstation",
                        "/calculateRevenue",
                        "/view_gasstations",
                        "/update",
                        UPDATE_GASSTATION)
                .hasAuthority("BUSINESS")
                .anyRequest().fullyAuthenticated();

        http.httpBasic().authenticationEntryPoint(ajaxAuthorizationPoint);

        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/pages/index.html");

        http.exceptionHandling().accessDeniedHandler(new AjaxAccessDeniedHandler());
    }
}
