package ma.exovate.medaibe.controllers;

import lombok.RequiredArgsConstructor;
import ma.exovate.medaibe.dtos.ScheduleDto;
import ma.exovate.medaibe.dtos.TimeSlotDTO;
import ma.exovate.medaibe.entities.Timeslot;
import ma.exovate.medaibe.exceptions.DoctorNotFoundException;
import ma.exovate.medaibe.exceptions.ScheduleNotFoundException;
import ma.exovate.medaibe.services.interfaces.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // Create a new schedule and generate time slots
    @PostMapping
    public ResponseEntity<ScheduleDto> createSchedule(@RequestBody ScheduleDto scheduleDto) throws DoctorNotFoundException {
        ScheduleDto createdSchedule = scheduleService.saveSchedule(scheduleDto);
        return ResponseEntity.ok(createdSchedule);
    }

    // Get a schedule by ID
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDto> getSchedule(@PathVariable Long scheduleId) throws ScheduleNotFoundException {
        ScheduleDto schedule = scheduleService.getSchedule(scheduleId);
        return ResponseEntity.ok(schedule);
    }

    // List all schedules
    @GetMapping
    public ResponseEntity<List<ScheduleDto>> listSchedules() {
        List<ScheduleDto> schedules = scheduleService.listSchedules();
        return ResponseEntity.ok(schedules);
    }

    // List all schedules by doctor
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<ScheduleDto>> listSchedulesByDoctor(@PathVariable Long doctorId) {
        List<ScheduleDto> schedules = scheduleService.listSchedulesByDoctor(doctorId);
        return ResponseEntity.ok(schedules);
    }

    // Delete a schedule by ID
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) throws ScheduleNotFoundException {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.noContent().build();
    }

    // List all time slots for a specific schedule
    @GetMapping("/{scheduleId}/timeslots")
    public ResponseEntity<List<TimeSlotDTO>> listTimeSlotsBySchedule(@PathVariable Long scheduleId) throws ScheduleNotFoundException {
        List<TimeSlotDTO> timeSlots = scheduleService.listTimeSlotsBySchedule(scheduleId);
        return ResponseEntity.ok(timeSlots);
    }

    // List available time slots for a doctor on a specific date
    @GetMapping("/doctor/{doctorId}/available-timeslots")
    public ResponseEntity<List<TimeSlotDTO>> listAvailableTimeSlots(
            @PathVariable Long doctorId,
            @RequestParam LocalDate date
    ) {
        List<TimeSlotDTO> availableTimeSlots = scheduleService.findAvailableSlotsByDoctorAndDate(doctorId, date);
        return ResponseEntity.ok(availableTimeSlots);
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<Timeslot> updateTimeSlotAvailability(
            @PathVariable Long id,
            @RequestParam boolean availability) {
        Timeslot updatedTimeSlot = scheduleService.updateTimeSlotAvailability(id, availability);
        return ResponseEntity.ok(updatedTimeSlot);
    }
}
