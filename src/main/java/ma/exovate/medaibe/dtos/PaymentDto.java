package ma.exovate.medaibe.dtos;

import java.time.LocalDateTime;

public class PaymentDto {
    private Long paymentId;

    private Long appointmentId;
    private String appointmentTitle;
    private LocalDateTime appointmentDate;

    private Long patientId;
    private String patientFirstName;
    private String patientLastName;

    private Double amount;
    private String paymentMethod;
    private LocalDateTime date;

}
