package repositories.customer;

import configuration.database.DataBaseContextConfiguration;
import model.Customer;
import model.Gender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import repository.CustomerRepository;
import repository.CustomerTypeRepository;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseContextConfiguration.class)
@ActiveProfiles(profiles = "dev")
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    private Customer initCustomer(){
        Customer customer = new Customer();
        customer.setId(1);
        customer.setCustomerType(customerTypeRepository.getByName("REGULAR"));
        customer.setDateOfBirth(new Date());
        customer.setEmail("blah@blah.com");
        customer.setPassword("password");
        customer.setFirstName("Blah");
        customer.setLastName("Blah");
        customer.setEnabled(true);
        customer.setGender(Gender.MALE);
        customer.setLogin("Blah");
        return customer;
    }

    @Test
    public void assertNotNullRepository() {
        assertNotNull(customerRepository);
    }

    @Test
    public void saveCustomer(){
        assertTrue(this.initCustomer().equals(customerRepository.save(this.initCustomer())));
    }

}
