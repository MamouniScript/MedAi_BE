package ma.exovate.medaibe.dtos;

import java.time.LocalDateTime;

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
}
