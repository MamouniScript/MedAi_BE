package ma.exovate.medaibe.services.interfaces;

import jakarta.transaction.Transactional;
import ma.exovate.medaibe.dtos.ScheduleDto;
import ma.exovate.medaibe.dtos.TimeSlotDTO;
import ma.exovate.medaibe.entities.Timeslot;
import ma.exovate.medaibe.exceptions.DoctorNotFoundException;
import ma.exovate.medaibe.exceptions.ScheduleNotFoundException;

import java.time.LocalDate;
import java.util.List;
@Transactional
public interface ScheduleService {
    // Create a new schedule and generate time slots
    ScheduleDto saveSchedule(ScheduleDto scheduleDto) throws DoctorNotFoundException;

    // Get a specific schedule by ID
    ScheduleDto getSchedule(Long scheduleId) throws ScheduleNotFoundException;

    // Delete a schedule and all associated time slots


    void deleteSchedule(Long scheduleId) throws ScheduleNotFoundException;
    ScheduleDto updateSchedule(ScheduleDto scheduleDTO) throws ScheduleNotFoundException;
    // List all schedules
    List<ScheduleDto> listSchedules();

    // List schedules for a specific doctor
    List<ScheduleDto> listSchedulesByDoctor(Long doctorId);

    // Find available time slots by doctor and date
    List<TimeSlotDTO> findAvailableSlotsByDoctorAndDate(Long doctorId, LocalDate date);

    // Mark a specific time slot as unavailable (e.g., booked)
    Timeslot updateTimeSlotAvailability(Long timeSlotId, boolean availability);

    // List all time slots for a specific schedule
    List<TimeSlotDTO> listTimeSlotsBySchedule(Long scheduleId) throws ScheduleNotFoundException;
}
