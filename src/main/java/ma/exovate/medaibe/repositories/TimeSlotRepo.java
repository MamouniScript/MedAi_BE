package ma.exovate.medaibe.repositories;

import ma.exovate.medaibe.entities.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TimeSlotRepo extends JpaRepository<Timeslot, Long> {
    List<Timeslot> findBytimeslotId(Long timeslotId);


    @Modifying
    @Query("DELETE FROM Timeslot t WHERE t.schedule.scheduleId = :scheduleId")
    void deleteByScheduleId(@Param("scheduleId") Long scheduleId);

    @Query("SELECT ts FROM Timeslot ts " +
            "WHERE ts.schedule.doctor.doctorId = :doctorId " +
            "AND ts.date = :date " +
            "AND ts.available = true")
    List<Timeslot> findAvailableSlotsByDoctorAndDate(@Param("doctorId") Long doctorId,
                                                     @Param("date") LocalDate date);

    List<Timeslot> findBySchedule_ScheduleId(Long scheduleId);
}