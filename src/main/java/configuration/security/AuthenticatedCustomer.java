package configuration.security;

import model.Customer;
import repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedCustomer {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer getAuthenticatedCustomer(){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return customerRepository.getByLogin(login);
    }
}
