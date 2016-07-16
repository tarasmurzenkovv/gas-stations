package repository;

import model.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelType, Integer>{
    @Query("select fuelType from FuelType fuelType where fuelType.typeName=:name")
    FuelType getByName(@Param("name")String name);
}
