package ma.exovate.medaibe.mappers;


import ma.exovate.medaibe.dtos.PatientDTO;
import ma.exovate.medaibe.dtos.PatientReq;
import ma.exovate.medaibe.entities.City;
import ma.exovate.medaibe.entities.Patient;

public class PatientMapper {

    public static Patient toEntity(PatientReq patientReq, City city) {
        return Patient.builder()
                .firstName(patientReq.getFirstName())
                .lastName(patientReq.getLastName())
                .email(patientReq.getEmail())
                .password(patientReq.getPassword())
                .city(city)
                .build();
    }

    public static PatientDTO toDTO(Patient patient) {
        return PatientDTO.builder()
                .patientId(patient.getPatientId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .email(patient.getEmail())

                .build();
    }
}
