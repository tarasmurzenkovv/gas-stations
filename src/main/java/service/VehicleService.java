package service;

import configuration.security.AuthenticatedCustomer;
import controllers.exceptions.CarTypeException;
import controllers.exceptions.VehicleException;
import controllers.vehicle.VehicleDto;
import model.type.CarType;
import model.Customer;
import model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CarTypeRepository;
import repository.CustomerRepository;
import repository.VehicleRepository;

import java.util.List;

@Service
@Transactional
public class VehicleService {
    @Autowired
    private CarTypeRepository carTypeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private AuthenticatedCustomer authenticatedCustomer;

    public void processAddingANewVehicle(VehicleDto vehicleDto) {
        CarType carType;
        Vehicle vehicle = new Vehicle();
        Customer customer = authenticatedCustomer.getAuthenticatedCustomer();
        carType = carTypeRepository.getByName(vehicleDto.getCarType());

        if (carType == null) {
            throw new CarTypeException("Unable to load car type for the provided string value: " + vehicleDto.getCarType());
        }

        vehicle.setVolume(vehicleDto.getVolume());
        vehicle.setIsDeleted(false);
        vehicle.setCarType(carType);
        vehicle.setRegistrationNumber(vehicleDto.getRegistrationNumber());
        vehicle.setCustomer(customer);

        if (customerRepository.getCustomerVehicles(customer).contains(vehicle)) {
            throw new VehicleException("You have already added such vehicle");
        }

        if(vehicleRepository.getByRegistrationNumber(vehicleDto.getRegistrationNumber()) != null){
            vehicle.setId(vehicleRepository.getByRegistrationNumber(vehicleDto.getRegistrationNumber()).getId());
        }
        vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Integer vehicleId){
        Vehicle vehicle = vehicleRepository.getOne(vehicleId);
        if (vehicle == null) {
            throw new VehicleException("There is no such vehicle for the given id: " + vehicleId);
        }
        vehicle.setIsDeleted(true);
        vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAuthenticatedCustomerVehicles() {
        Customer loggedInCustomer = authenticatedCustomer.getAuthenticatedCustomer();
        return customerRepository.getCustomerVehicles(loggedInCustomer);
    }

}
