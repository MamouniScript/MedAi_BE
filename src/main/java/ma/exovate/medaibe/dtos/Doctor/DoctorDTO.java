package ma.exovate.medaibe.dtos.Doctor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDTO {

    private Long doctorId;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String email;
    private String pitch;
    private Double balance;
    private Double commission;
    private String specializationName; // Nom de la spécialité pour la présentation.
    private String cityName;          // Nom de la ville pour la présentation.
    private Double averageRating;
    private Integer totalReviews;
    private Integer appointmentCount;

}
