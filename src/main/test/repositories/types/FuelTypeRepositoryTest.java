package repositories.types;


import configuration.database.DataBaseContextConfiguration;
import model.type.FuelType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import repository.FuelTypeRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static util.TypesBuilder.getFuelTypes;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseContextConfiguration.class)
@ActiveProfiles(profiles = "dev")
public class FuelTypeRepositoryTest {
    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    @Test
    public void assertNotNullRepository(){
        assertNotNull(fuelTypeRepository);
    }

    @Test
    public void getTestingData(){
        List<FuelType> actualFuelTypes = fuelTypeRepository.findAll();
        assertEquals(actualFuelTypes, getFuelTypes());
    }
}
