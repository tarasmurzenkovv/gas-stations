package repositories.gasstation;

import configuration.database.DataBaseContextConfiguration;
import model.Fueling;
import model.GasStation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import repository.FuelTypeRepository;
import repository.FuellingRepository;
import repository.GasStationRepository;
import repository.VehicleRepository;
import service.Revenue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseContextConfiguration.class)
public class GasStationRepositoryTest {
    @Autowired
    private GasStationRepository gasStationRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private FuelTypeRepository fuelTypeRepository;
    @Autowired
    private FuellingRepository fuellingRepository;

    private Fueling initFueling(Integer rating, String gasStationName) {
        Fueling fueling = new Fueling();
        fueling.setVolume(20);
        fueling.setPrice(new BigDecimal(10));
        fueling.setRating(rating);
        fueling.setComment("blah");
        fueling.setFuelType(fuelTypeRepository.getByName("A-72"));
        fueling.setDate(new Date());
        fueling.setGasStation(gasStationRepository.getByName(gasStationName));
        fueling.setVehicle(vehicleRepository.getByRegistrationNumber("AA1111BB"));

        return fuellingRepository.save(fueling);
    }

    @Before
    public void insertData() {
        for (int i = 20; i < 40; i++) {
            initFueling(i, "GS1");
        }

        for (int i = 20; i < 40; i++) {
            initFueling(i, "GS2");
        }

        for (int i = 100; i < 200; i += 10) {
            initFueling(i, "GS2");
        }
    }

    @Test
    public void selectByMaximumRating() {
        List<GasStation> topRated = gasStationRepository.findTheMostRatedGasStations(java.sql.Date.valueOf("2015-02-01"), 1);
        GasStation gs2 = gasStationRepository.getByName("GS2");
        assertTrue(topRated.contains(gs2));
    }

    @Test
    public void calculateRevenue() {
        List<Revenue> revenues =
                gasStationRepository.calculateRevenueForGasStationName(java.sql.Date.valueOf("2015-02-01"), "GS2")
                        .stream()
                        .map(object -> (Object[]) object)
                        .map(objects -> {
                            Revenue revenue = new Revenue();
                            revenue.setDate(java.sql.Date.valueOf(objects[0].toString()));
                            revenue.setRevenuePerDate(new BigDecimal((Double) objects[1]));
                            return revenue;
                        })
                        .collect(Collectors.toList());
        Revenue expected = new Revenue();
        expected.setRevenuePerDate(new BigDecimal(200));
        Date e = new Date();
        expected.setDate(java.sql.Date.valueOf(e.toString()));
        Revenue actual = revenues.get(0);

        assertTrue(actual.getDate().toString().equals(expected.getDate().toString()));
        assertTrue(actual.getRevenuePerDate().toString().equals(expected.getRevenuePerDate().toString()));
    }
}
