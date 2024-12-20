package ma.exovate.medaibe.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private Long appointmentId;

    private String title;
    private String description;

    private String video; // VisioConf call link

    private Long doctorId;
    private String doctor_fN;
    private String doctor_lN;

    private Long patientId;
    private String patient_fN;
    private String patient_lN;

    private Double duration;
    private LocalDateTime date;
    private String status;
}
