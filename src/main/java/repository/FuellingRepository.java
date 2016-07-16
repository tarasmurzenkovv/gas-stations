package repository;

import model.Fueling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuellingRepository extends JpaRepository<Fueling, Integer> {
}
