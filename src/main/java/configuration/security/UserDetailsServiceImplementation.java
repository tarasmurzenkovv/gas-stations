package configuration.security;

import model.Customer;
import repository.CustomerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsServiceImplementation implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(UserDetailsServiceImplementation.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Customer customer = customerRepository.getByLogin(login);

        if (customer == null) {
            String message = "Username not found" + login;
            LOGGER.info(message);
            throw new UsernameNotFoundException(message);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        if("BUSINESS".equals(customer.getCustomerType().getTypeName())){
            authorities.add(new SimpleGrantedAuthority("BUSINESS"));
        }else{
            authorities.add(new SimpleGrantedAuthority("REGULAR"));
        }
        LOGGER.info("Found user in database: " + customer);
        return new org.springframework.security.core.userdetails.User(login, customer.getPassword(), authorities);
    }
}
