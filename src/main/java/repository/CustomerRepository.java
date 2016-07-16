package repository;

import model.Customer;
import model.Fueling;
import model.Vehicle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("select customer from Customer customer where customer.login=:login")
    Customer getByLogin(@Param("login") String login);

    @Query("select vehicle from Vehicle vehicle where vehicle.customer=:customer")
    List<Vehicle> getCustomerVehicles(@Param("customer") Customer customer);

    @Query("select count (fueling) from Fueling fueling where " +
            "fueling.vehicle in (select vehicle from Vehicle vehicle where vehicle.customer=:customer)")
    Integer numberOfFuelings(@Param("customer") Customer customer);

    @Query("select fueling from Fueling fueling where " +
            "fueling.vehicle in (select vehicle from Vehicle vehicle where vehicle.customer=:customer) ")
    List<Fueling> getFuelingsPageble(@Param("customer") Customer customer, Pageable pageable);

    @Query("select case when count(customer) > 0 then true else false end from Customer customer where customer.login=:login")
    Boolean checkIfLoginExistsInDatabase(@Param("login") String customerLogin);

    @Query("select case when count(customer) > 0 then true else false end from Customer customer where customer.email=:email")
    Boolean checkIfEmailExistsInDatabase(@Param("email") String customerEmail);
}
