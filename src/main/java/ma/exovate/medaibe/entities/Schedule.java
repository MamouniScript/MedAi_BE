package ma.exovate.medaibe.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @ManyToOne
    @JoinColumn(name = "doctorId", nullable = false)
    private Doctor doctor;

    private LocalDate date; // Date of availability
    private LocalTime startTime; // Start time of availability
    private LocalTime endTime; // End time of availability
    private Integer sessionDuration; // Duration in minutes
    private Integer breakDuration; // Break between sessions in minutes

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Timeslot> timeSlots;
}
