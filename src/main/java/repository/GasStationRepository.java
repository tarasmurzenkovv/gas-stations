package repository;

import model.Customer;
import model.GasStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface GasStationRepository extends JpaRepository<GasStation, Integer> {

    @Query("select customer from GasStation gasStation  ")
    List<Customer> getCustomers(@Param("gas_station") GasStation gasStation);

    @Query("select gasStation from GasStation gasStation where gasStation.name=:name")
    GasStation getByName(@Param("name") String name);

    @Query(value = "select gas_station.id, gas_station.name, gas_station.address, gas_station.trademark, gas_station.customer, " +
            "avg(fueling.rating) as average from gas_station " +
            "left join fueling on gas_station.id = fueling.gas_station " +
            "where fueling.date between ?1 and curdate() " +
            "group by gas_station.id " +
            "order by average desc "+
            "limit ?2",
            nativeQuery = true)
    List<GasStation> findTheMostRatedGasStations(Date date, Integer numberToShow);

    @Query(value = "select gas_station.id, gas_station.name, gas_station.address, gas_station.trademark, gas_station.customer, " +
            "avg(fueling.rating) as average from gas_station " +
            "left join fueling on gas_station.id = fueling.gas_station " +
            "where fueling.date between ?1 and curdate() " +
            "group by gas_station.id " +
            "order by average desc ",
            nativeQuery = true)
    List<GasStation> findTheMostRatedGasStations(Date date);

    @Query(value = "select fueling.date, fueling.price*fueling.volume as revenue from gas_station " +
            "left join fueling " +
            "on gas_station.id = fueling.gas_station " +
            "where fueling.date between ?1 and curdate() and gas_station.name=?2 " +
            "group by fueling.date", nativeQuery = true)
    List<Object> calculateRevenueForGasStationName(Date date, String name);

    @Query(value = "select fueling.date, fueling.price*fueling.volume as revenue from gas_station " +
            "left join fueling " +
            "on gas_station.id = fueling.gas_station " +
            "where fueling.date between ?1 and ?2 and gas_station.name=?3 " +
            "group by fueling.date", nativeQuery = true)
    List<Object> calculateRevenueForGasStationName(Date from, Date to, String name);

    @Query("select case when count(gas_station) > 0 then true else false end " +
            "from GasStation gas_station where gas_station.name=:name")
    Boolean checkIfGasStationNameExists(@Param("name") String name);
}
