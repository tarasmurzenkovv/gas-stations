package repositories.types;

import configuration.database.DataBaseContextConfiguration;
import model.type.CustomerType;
import model.type.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import repository.CustomerTypeRepository;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseContextConfiguration.class)
@ActiveProfiles(profiles = "dev")
public class CustomerTypeRepositoryTest {
    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    List<Type> getExpectedCustomerTypes() {
        List<Type> customerTypes = new ArrayList<>();

        Type regular = new CustomerType();
        regular.setId(1);
        regular.setTypeName("REGULAR");
        customerTypes.add(regular);

        Type business = new CustomerType();
        business.setId(2);
        business.setTypeName("BUSINESS");
        customerTypes.add(business);

        return customerTypes;

    }

    @Test
    public void assertNotNullRepository() {
        assertNotNull(customerTypeRepository);
    }

    @Test
    public void getTestingData() {
        List<CustomerType> customerTypes = customerTypeRepository.findAll();
        assertEquals(customerTypes, getExpectedCustomerTypes());
    }
}
