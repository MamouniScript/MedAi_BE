package ma.exovate.medaibe.dtos.Doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorReq {
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String cin;
    private Date birthdate;
    private String birthPlace;
    private String email;
    private String password;
    private String pitch;
    private Date graduationYear;
    private Boolean verification;
    private String documents;
    private Double balance;
    private Double commission;
    private Long specializationId; // Référence à la spécialité.
    private Long scheduleId;       // Référence à l'emploi du temps.
    private Long cityId;
}
