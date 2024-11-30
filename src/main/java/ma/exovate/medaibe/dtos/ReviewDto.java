package ma.exovate.medaibe.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long reviewId;

    private Long doctorId;
    private String doctor_fN;
    private String doctor_lN;

    private Long patientId;
    private String patient_fN;
    private String patient_lN;

    private Long appointmentId;

    private Integer rating; // Rating out of 5
    private String comment; // Optional feedback from the patient
    private LocalDateTime date; // Date of the review
}
