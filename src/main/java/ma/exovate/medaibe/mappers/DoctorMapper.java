package ma.exovate.medaibe.mappers;

import ma.exovate.medaibe.dtos.DoctorDTO;
import ma.exovate.medaibe.dtos.DoctorReq;
import ma.exovate.medaibe.entities.City;
import ma.exovate.medaibe.entities.Doctor;
import ma.exovate.medaibe.entities.Speciality;

public class DoctorMapper {

    // Convertir DoctorReq -> Doctor
    public static Doctor toEntity(DoctorReq doctorReq, City city, Speciality specialization) {
        return Doctor.builder()
                .firstName(doctorReq.getFirstName())
                .lastName(doctorReq.getLastName())
                .gender(doctorReq.getGender())
                .phone_Number(doctorReq.getPhoneNumber())
                .cin(doctorReq.getCin())
                .birthdate(doctorReq.getBirthdate())
                .birth_Place(doctorReq.getBirthPlace())
                .email(doctorReq.getEmail())
                .password(doctorReq.getPassword())
                .pitch(doctorReq.getPitch())
                .graduation_year(doctorReq.getGraduationYear())
                .verification(doctorReq.getVerification())
                .documents(doctorReq.getDocuments())
                .balance(doctorReq.getBalance())
                .commission(doctorReq.getCommission())
                .city(city)
                .specialization(specialization)
                .build();
    }

    // Convertir Doctor -> DoctorDTO
    public static DoctorDTO toDTO(Doctor doctor) {
        return DoctorDTO.builder()
                .doctorId(doctor.getDoctorId())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .gender(doctor.getGender())
                .phoneNumber(doctor.getPhone_Number())


                .email(doctor.getEmail())
                .pitch(doctor.getPitch())



                .balance(doctor.getBalance())
                .commission(doctor.getCommission())


                .averageRating(doctor.getAverageRating())
                .totalReviews(doctor.getTotalReviews())
                .appointmentCount(doctor.getAppointmentCount())
                .build();
    }
}
