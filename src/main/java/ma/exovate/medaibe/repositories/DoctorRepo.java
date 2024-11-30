package ma.exovate.medaibe.repositories;

import ma.exovate.medaibe.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
}
