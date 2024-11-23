package ma.exovate.medaibe.repositories;

import ma.exovate.medaibe.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {
}
