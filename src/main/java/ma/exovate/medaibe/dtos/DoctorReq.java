package ma.exovate.medaibe.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorReq {
    private Long doctorId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String documents;
    private Long specializationId;
    private Long cityId;
}
