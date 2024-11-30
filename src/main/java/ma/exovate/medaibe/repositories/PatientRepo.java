package ma.exovate.medaibe.repositories;

import ma.exovate.medaibe.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient, Long> {
}
