package service;

import controllers.exceptions.CustomerException;
import controllers.exceptions.GasStationException;
import controllers.gasstation.GasStationDto;
import model.Customer;
import model.type.FuelType;
import model.GasStation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import repository.CustomerRepository;
import repository.FuelTypeRepository;
import repository.GasStationRepository;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@code GasStationService} encapsulates methods that could full fill manager of gas station(s) requirements.
 * This class serves to
 * It uses louse coupling concept between DAO interfaces and business logic via passing all relevant DAO interfaces
 * in constructor.
 * <p>
 * Implements the following:
 */
@Service
@Transactional
public class GasStationService {
    @Autowired
    private GasStationRepository gasStationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    private static final Logger logger = Logger.getLogger(GasStationService.class);

    public List<Revenue> calculateRevenueForDateInterval(String gasStationName, Date from, Date to) {
        logger.debug("Rest is being accessed. Calculating revenue for business type customer. ");

        if (from.after(to)) {
            throw new CustomerException("'From' date cannot be after 'to' date.");
        }

        if (gasStationRepository.checkIfGasStationNameExists(gasStationName)) {
            throw new CustomerException("No gas station was found for a given name: " + gasStationName);
        }

        return gasStationRepository.calculateRevenueForGasStationName(
                java.sql.Date.valueOf(from.toString()),
                java.sql.Date.valueOf(to.toString()),
                gasStationName).stream()
                .map(object -> (Object[]) object)
                .map(objects -> {
                    Revenue revenue = new Revenue();
                    revenue.setDate(java.sql.Date.valueOf(objects[0].toString()));
                    revenue.setRevenuePerDate(new BigDecimal((Double) objects[1]));
                    return revenue;
                })
                .collect(Collectors.toList());
    }

    public void addGasStation(GasStationDto gasStationDto) {
        GasStation gasStation = new GasStation();
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.getByLogin(login);

        gasStation.setName(gasStationDto.getName());
        gasStation.setAddress(gasStationDto.getAddress());
        gasStation.setCompanyName(gasStationDto.getCompanyName());
        gasStation.setCustomer(customer);

        if (customer.getGasstations().contains(gasStation)) {
            throw new GasStationException("You have already added such gas station");
        }

        gasStationRepository.save(gasStation);
    }

    public void performUpdate(GasStationDto gasStationDto) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.getByLogin(login);
        GasStation gasStation = gasStationRepository.getOne(gasStationDto.getId());
        gasStation.setName(gasStationDto.getName());
        gasStation.setAddress(gasStationDto.getAddress());
        gasStation.setCompanyName(gasStationDto.getCompanyName());

        if (customer.getGasstations().contains(gasStation)) {
            throw new GasStationException("Consider another naming");
        }

        gasStationRepository.save(gasStation);
    }

    public GasStation loadInfo(Integer gasStationId) {
        GasStation gasStation = gasStationRepository.getOne(gasStationId);
        if (gasStation == null) {
            throw new GasStationException("No gas station was found for a given id: " + gasStationId);
        }
        return gasStation;
    }

    public Map<String, GasStation> findRelevantGasStations(@Valid @RequestParam(value = "name") String fuelTypeName) {
        Map<String, GasStation> interestingGasStations = new HashMap<>();
        FuelType fuelType = fuelTypeRepository.getByName(fuelTypeName);

        interestingGasStations.put("cheapest", customerService.getLeastExpensiveGasStation(fuelType));
        interestingGasStations.put("rated", customerService.getTheMostPopularGasStation());
        interestingGasStations.put("popular", customerService.getTheMostRatedGasStation());
        return interestingGasStations;
    }
}
