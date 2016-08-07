package service;

import configuration.security.AuthenticatedCustomer;
import controllers.fueling.FuelingDto;
import model.*;
import model.type.FuelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import repository.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FuelingService {
    @Autowired
    private FuellingRepository fuellingRepository;
    @Autowired
    private FuelTypeRepository fuelTypeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private GasStationRepository gasStationRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private AuthenticatedCustomer authenticatedCustomer;
    @Autowired
    private Integer numberOfEntriesToDisplay;

    public void addANewFueling(FuelingDto fuelingDto) {
        Fueling fueling = new Fueling();
        Customer customer = authenticatedCustomer.getAuthenticatedCustomer();
        FuelType fuelType = fuelTypeRepository.getByName(fuelingDto.getFuelType());
        Vehicle vehicle = vehicleRepository.getVehicle(customer, fuelingDto.getVehicleRegistrationNumber());
        GasStation gasStation = gasStationRepository.getByName(fuelingDto.getGasStationName());

        fueling.setVolume(fuelingDto.getVolume());
        fueling.setPrice(fuelingDto.getPrice());
        fueling.setDate(fuelingDto.getDate());
        fueling.setComment(fuelingDto.getComment());
        fueling.setFuelType(fuelType);
        fueling.setVehicle(vehicle);
        fueling.setGasStation(gasStation);
        fueling.setRating(fuelingDto.getRating());
        fuellingRepository.save(fueling);
    }

    public Map<String, Object> getData() {
        Map<String, Object> data = new HashMap<>();
        data.put("vehicles", customerRepository.getCustomerVehicles(authenticatedCustomer.getAuthenticatedCustomer()));
        data.put("fuel_types", fuelTypeRepository.findAll());
        data.put("gas_stations", gasStationRepository.findAll());
        return data;
    }

    public List<FuelingDto> getFuelingsPerPages(Integer pageNumber) {
        Customer customer = this.authenticatedCustomer.getAuthenticatedCustomer();

/*        return customerService.getFuelingsPerPage(customer, pageNumber)
                .stream()
                .map(fueling -> {
                    FuelingDto fuelingDto = new FuelingDto();
                    fuelingDto.setId(fueling.getId());
                    fuelingDto.setPrice(fueling.getPrice());
                    fuelingDto.setVolume(fueling.getVolume());
                    fuelingDto.setFuelType(fueling.getFuelType().getTypeName());
                    fuelingDto.setGasStationName(fueling.getGasStation().getCompanyName());
                    fuelingDto.setVehicleRegistrationNumber(fueling.getVehicle().getRegistrationNumber());
                    fuelingDto.setDate(fueling.getDate());
                    fuelingDto.setRating(fueling.getRating());
                    return fuelingDto;
                }).collect(Collectors.toList());*/

        return new ArrayList<>();
    }

    public Integer getNumberOfPages() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.getByLogin(login);
        int numberOfFuelings = customerRepository.numberOfFuelings(customer);
        return numberOfFuelings / numberOfEntriesToDisplay + (numberOfFuelings % numberOfEntriesToDisplay == 0 ? 0 : 1);
    }

    public void markFuelingEntryAsDeleted(Integer fuelingId) {
        Fueling foundFueling = fuellingRepository.getOne(fuelingId);
        foundFueling.setDeleted(true);
        fuellingRepository.save(foundFueling);
    }
}
