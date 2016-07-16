package repository;

import model.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTypeRepository extends JpaRepository<CustomerType, Integer>{
    @Query("select customerType from CustomerType customerType where customerType.typeName=:name")
    CustomerType getByName(@Param("name")String name);
}
