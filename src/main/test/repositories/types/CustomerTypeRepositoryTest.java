package repositories.types;

import configuration.database.DataBaseContextConfiguration;
import model.type.CustomerType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import repository.CustomerTypeRepository;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static util.TypesBuilder.getCustomerTypes;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseContextConfiguration.class)
@ActiveProfiles(profiles = "dev")
public class CustomerTypeRepositoryTest {
    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    @Test
    public void assertNotNullRepository() {
        assertNotNull(customerTypeRepository);
    }

    @Test
    public void getTestingData() {
        List<CustomerType> customerTypes = customerTypeRepository.findAll();
        assertEquals(customerTypes, getCustomerTypes());
    }
}
