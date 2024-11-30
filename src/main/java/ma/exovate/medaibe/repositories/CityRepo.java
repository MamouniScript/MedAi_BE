package ma.exovate.medaibe.repositories;

import ma.exovate.medaibe.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City, Long> {
}
