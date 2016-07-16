package repositories.types;

import configuration.database.DataBaseContextConfiguration;
import model.CarType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import repository.CarTypeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseContextConfiguration.class)
public class CarTypeRepositoryTest {
    @Autowired
    private CarTypeRepository carTypeRepository;

    private List<CarType> getExpectedCarTypes() {
        List<CarType> expectedCarTypes = new ArrayList<>();
        CarType toyota = new CarType();
        toyota.setId(1);
        toyota.setTypeName("Toyota");
        CarType mercedes = new CarType();
        mercedes.setId(2);
        mercedes.setTypeName("Mercedes");
        CarType volvo = new CarType();
        volvo.setId(3);
        volvo.setTypeName("Volvo");
        CarType peugeot = new CarType();
        peugeot.setId(4);
        peugeot.setTypeName("Peugeot");
        CarType renault = new CarType();
        renault.setId(5);
        renault.setTypeName("Renault");
        expectedCarTypes.add(toyota);
        expectedCarTypes.add(mercedes);
        expectedCarTypes.add(volvo);
        expectedCarTypes.add(peugeot);
        expectedCarTypes.add(renault);
        return expectedCarTypes;
    }

    @Test
    public void assertNotNullRepository(){
        assertNotNull(carTypeRepository);
    }

    @Test
    public void getTestingData(){
        List<CarType> expectedCarTypes = getExpectedCarTypes();
        List<CarType> actualCarTypes = carTypeRepository.findAll();
        assertEquals(expectedCarTypes,actualCarTypes);
    }

}
