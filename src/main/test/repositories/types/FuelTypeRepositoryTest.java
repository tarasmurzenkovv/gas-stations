package repositories.types;


import configuration.database.DataBaseContextConfiguration;
import model.FuelType;
import model.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import repository.FuelTypeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseContextConfiguration.class)
public class FuelTypeRepositoryTest {
    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    private List<Type> getExpectedFuelTypes(){
        List<Type> expectedFuelTypes = new ArrayList<>();

        Type a72 = new FuelType();
        a72.setId(1);
        a72.setTypeName("A-72");
        expectedFuelTypes.add(a72);

        Type a76 = new FuelType();
        a76.setId(1);
        a76.setTypeName("A-76");
        expectedFuelTypes.add(a76);

        Type a80 = new FuelType();
        a80.setId(3);
        a80.setTypeName("A-80");
        expectedFuelTypes.add(a80);

        Type ai91 = new FuelType();
        ai91.setId(4);
        ai91.setTypeName("AI-91");
        expectedFuelTypes.add(ai91);

        Type ai93 = new FuelType();
        ai93.setId(5);
        ai93.setTypeName("AI-93");
        expectedFuelTypes.add(ai93);

        Type ai95 = new FuelType();
        ai95.setId(6);
        ai95.setTypeName("AI-95");
        expectedFuelTypes.add(ai95);

        return expectedFuelTypes;
    }

    @Test
    public void assertNotNullRepository(){
        assertNotNull(fuelTypeRepository);
    }

    @Test
    public void getTestingData(){
        List<FuelType> actualFuelTypes = fuelTypeRepository.findAll();
        assertEquals(actualFuelTypes,getExpectedFuelTypes());
    }
}
