package ma.exovate.medaibe.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {

    private Long reportId;

    private Long appointmentId;
    private String appointmentTitle;
    private String appointmentDescription;
    private LocalDateTime appointmentDate;

    private Long doctorId;
    private String doctorFirstName;
    private String doctorLastName;

    private Long patientId;
    private String patientFirstName;
    private String patientLastName;

    private String diagnosis;
    private String recommendations;

    private String link;
}
