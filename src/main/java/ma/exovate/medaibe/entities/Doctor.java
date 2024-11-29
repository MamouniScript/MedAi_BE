package ma.exovate.medaibe.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Date;
import java.util.Objects;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    private String firstName;
    private String lastName;
    private String gender;

    private String phone_Number;

    private String cin;
    private Date birthdate;
    private String birth_Place;

    private String email;
    private String password;

    private String pitch;

    private Date graduation_year;

    private Boolean verification;
    private String documents;

    private Double balance;
    private Double commission;

    @ManyToOne
    @JoinColumn(name = "specializationId", nullable = false)
    private Speciality specialization;

    @ManyToOne
    @JoinColumn(name = "scheduleId")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "cityId", nullable = false)
    private City city;

    private Double averageRating = 0.0;
    private Integer totalReviews = 0;
    private Integer appointmentCount = 0;


}

