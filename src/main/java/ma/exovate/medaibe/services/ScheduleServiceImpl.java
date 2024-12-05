package ma.exovate.medaibe.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.exovate.medaibe.dtos.ScheduleDto;
import ma.exovate.medaibe.dtos.TimeSlotDTO;
import ma.exovate.medaibe.entities.Doctor;
import ma.exovate.medaibe.entities.Schedule;
import ma.exovate.medaibe.entities.Timeslot;
import ma.exovate.medaibe.exceptions.DoctorNotFoundException;
import ma.exovate.medaibe.exceptions.ScheduleNotFoundException;
import ma.exovate.medaibe.mappers.ScheduleMapper;
import ma.exovate.medaibe.mappers.TimeSlotMapper;
import ma.exovate.medaibe.repositories.DoctorRepo;
import ma.exovate.medaibe.repositories.ScheduleRepo;
import ma.exovate.medaibe.repositories.TimeSlotRepo;
import ma.exovate.medaibe.services.interfaces.ScheduleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

    private final  ScheduleRepo scheduleRepository;
    private final TimeSlotRepo timeSlotRepository;
    private final DoctorRepo doctorRepository;
    private final ScheduleMapper dtoMapper;
    private final TimeSlotMapper timeslotMapper;
    /**
     * Save a new schedule and generate corresponding time slots.
     */
    @Override
    public ScheduleDto saveSchedule(ScheduleDto scheduleDTO) throws DoctorNotFoundException {
        // Map the ScheduleDTO to a Schedule entity
        Schedule schedule = dtoMapper.fromScheduleDto(scheduleDTO);

        // Find the doctor and assign to the schedule
        Doctor doctor = doctorRepository.findById(scheduleDTO.getDoctorId())
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));
        schedule.setDoctor(doctor);

        // Save the schedule
        Schedule savedSchedule = scheduleRepository.save(schedule);

        // Generate time slots and save them
        generateTimeSlots(savedSchedule);

        // Map the saved schedule back to a DTO and return
        return dtoMapper.fromSchedule(savedSchedule);
    }

    /**
     * Generate time slots for a given schedule.
     */
    private void generateTimeSlots(Schedule schedule) {
        List<Timeslot> timeSlots = new ArrayList<>();
        LocalTime currentStartTime = schedule.getStartTime();
        LocalTime endTime = schedule.getEndTime();

        while (currentStartTime.isBefore(endTime)) {
            LocalTime nextEndTime = currentStartTime.plusMinutes(schedule.getSessionDuration());
            if (nextEndTime.isAfter(endTime)) break;

            Timeslot timeSlot = new Timeslot();
            timeSlot.setSchedule(schedule);
            timeSlot.setDate(schedule.getDate());
            timeSlot.setStartTime(currentStartTime);
            timeSlot.setEndTime(nextEndTime);
            timeSlot.setAvailable(true);

            timeSlots.add(timeSlot);
            currentStartTime = nextEndTime.plusMinutes(schedule.getBreakDuration());
        }

        timeSlotRepository.saveAll(timeSlots);
    }

    /**
     * Update an existing schedule.
     */
    @Override
    public ScheduleDto updateSchedule(ScheduleDto scheduleDTO) throws ScheduleNotFoundException {
        // Find the existing schedule
        Schedule existingSchedule = scheduleRepository.findById(scheduleDTO.getScheduleId())
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found"));

        // Map the DTO to the entity and update fields
        Schedule updatedSchedule = dtoMapper.fromScheduleDto(scheduleDTO);
        updatedSchedule.setDoctor(existingSchedule.getDoctor());

        // Save the updated schedule
        Schedule savedSchedule = scheduleRepository.save(updatedSchedule);

        // Regenerate time slots
        timeSlotRepository.deleteByScheduleId(savedSchedule.getScheduleId());
        generateTimeSlots(savedSchedule);

        return dtoMapper.fromSchedule(savedSchedule);
    }

    /**
     * Delete a schedule and its associated time slots.
     */

    @Transactional
    @Override
    public void deleteSchedule(Long scheduleId) throws ScheduleNotFoundException {
        // Ensure the schedule exists
        scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found"));

        // Delete time slots and schedule
        timeSlotRepository.deleteByScheduleId(scheduleId);
        scheduleRepository.deleteById(scheduleId);
    }

    /**
     * Get all schedules for a doctor.
     */
    @Override
    public List<ScheduleDto> listSchedulesByDoctor(Long doctorId) {
        return scheduleRepository.findByDoctor_DoctorId(doctorId)
                .stream()
                .map(dtoMapper::fromSchedule)
                .collect(Collectors.toList());
    }

    /**
     * Mark a time slot as unavailable.
     */
    @Override
    public Timeslot updateTimeSlotAvailability(Long timeSlotId, boolean availability) {
        // Find the timeslot by ID
        Timeslot timeSlot = timeSlotRepository.findById(timeSlotId)
                .orElseThrow(() -> new RuntimeException("TimeSlot not found with ID: " + timeSlotId));

        // Update availability
        timeSlot.setAvailable(availability);

        // Save and return the updated time slot
        return timeSlotRepository.save(timeSlot);
    }

    /**
     * Get all available time slots for a given doctor and date.
     */
    @Override
    public List<TimeSlotDTO> findAvailableSlotsByDoctorAndDate(Long doctorId, LocalDate date) {
        List<Timeslot> timeSlots = timeSlotRepository.findAvailableSlotsByDoctorAndDate(doctorId, date);
        return timeSlots.stream().map(timeslotMapper::fromTimeSlot).collect(Collectors.toList());
    }

    @Override
    public ScheduleDto getSchedule(Long scheduleId) throws ScheduleNotFoundException {
        // Retrieve the schedule from the repository
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule with ID " + scheduleId + " not found"));

        // Map the Schedule entity to ScheduleDto
        return dtoMapper.fromSchedule(schedule);
    }

    @Override
    public List<TimeSlotDTO> listTimeSlotsBySchedule(Long scheduleId) throws ScheduleNotFoundException {
        // Ensure the schedule exists
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule with ID " + scheduleId + " not found"));

        // Retrieve all time slots for the schedule
        List<Timeslot> timeSlots = timeSlotRepository.findBySchedule_ScheduleId(scheduleId);

        // Map the list of TimeSlot entities to a list of TimeSlotDto objects
        return timeSlots.stream()
                .map(timeslotMapper::fromTimeSlot)
                .collect(Collectors.toList());
    }


    @Override
    public List<ScheduleDto> listSchedules() {
        // Retrieve all schedules from the repository
        List<Schedule> schedules = scheduleRepository.findAll();

        // Map the list of Schedule entities to a list of ScheduleDto objects
        return schedules.stream()
                .map(dtoMapper::fromSchedule)
                .collect(Collectors.toList());
    }


}
