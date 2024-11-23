package ma.exovate.medaibe.repositories;

import ma.exovate.medaibe.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
}
