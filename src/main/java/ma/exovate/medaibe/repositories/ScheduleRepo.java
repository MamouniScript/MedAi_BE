package ma.exovate.medaibe.repositories;

import ma.exovate.medaibe.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {
    List<Schedule> findByScheduleId(Long doctorId);
    List<Schedule> findByDoctor_DoctorId(Long doctorId);
}
