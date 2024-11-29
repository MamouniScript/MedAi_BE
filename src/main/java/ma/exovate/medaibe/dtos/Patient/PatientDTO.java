package ma.exovate.medaibe.dtos.Patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDTO {
    private Long patientId;
    private String firstName;
    private String lastName;
    private String email;
    private String cityName;
}
