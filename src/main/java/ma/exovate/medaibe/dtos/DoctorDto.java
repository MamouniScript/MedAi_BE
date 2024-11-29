package ma.exovate.medaibe.dtos;

import java.util.Date;

public class DoctorDto {

    private Long doctorId;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String nni;
    private Date birthdate;
    private String birthPlace;
    private String email;
    private String pitch;
    private Date graduationYear;
    private Boolean verification;
    private String documents;
    private Double balance;
    private Double commission;

    private Long specializationId;
    private String specializationName;

    private Long scheduleId;

    private Long cityId;
    private String cityName;

    private Double averageRating;
    private Integer totalReviews;
    private Integer appointmentCount;

    // Getters and setters
}
