package repository;

import model.Customer;
import model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

    @Query("select vehicle from Vehicle vehicle where vehicle.registrationNumber=:number")
    Vehicle getByRegistrationNumber(@Param("number")String registrationNumber);

    @Query("select vehicle from Vehicle vehicle where vehicle.registrationNumber=:number and vehicle.customer=:customer")
    Vehicle getVehicle(@Param("customer")Customer customer,@Param("number")String registrationNumber);
}
