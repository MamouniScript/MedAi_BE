package ma.exovate.medaibe.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
