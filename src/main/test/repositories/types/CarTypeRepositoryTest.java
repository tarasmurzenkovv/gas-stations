package repositories.types;

import configuration.database.DataBaseContextConfiguration;
import model.type.CarType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import repository.CarTypeRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static util.TypesBuilder.getCarTypes;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseContextConfiguration.class)
@ActiveProfiles(profiles = "dev")
public class CarTypeRepositoryTest {
    @Autowired
    private CarTypeRepository carTypeRepository;

    @Test
    public void assertNotNullRepository(){
        assertNotNull(carTypeRepository);
    }

    @Test
    public void getTestingData(){
        List<CarType> expectedCarTypes = getCarTypes();
        List<CarType> actualCarTypes = carTypeRepository.findAll();
        assertEquals(expectedCarTypes,actualCarTypes);
    }

}
