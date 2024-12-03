package ma.exovate.medaibe.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientReq {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long cityId;
}
