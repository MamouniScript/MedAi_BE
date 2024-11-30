package ma.exovate.medaibe.mappers;

import ma.exovate.medaibe.dtos.AppointmentDto;
import ma.exovate.medaibe.dtos.AppointmentReq;
import ma.exovate.medaibe.entities.Appointment;
import ma.exovate.medaibe.entities.Doctor;
import ma.exovate.medaibe.entities.Patient;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    // Converts AppointmentReq to Appointment (Entity)
    public Appointment repToEntity(AppointmentReq req, Doctor doctor, Patient patient) {
        Appointment appointment = new Appointment();
        appointment.setTitle(req.getTitle());
        appointment.setDescription(req.getDescription());
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setDate(req.getDate());
        appointment.setStatus(req.getStatus());
        return appointment;
    }

    // Converts AppointmentDto to Appointment (Entity)
    public Appointment toEntity(AppointmentDto dto, Doctor doctor, Patient patient) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(dto.getAppointmentId());
        appointment.setTitle(dto.getTitle());
        appointment.setDescription(dto.getDescription());
        appointment.setVideo(dto.getVideo());
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setDuration(dto.getDuration());
        appointment.setDate(dto.getDate());
        appointment.setStatus(dto.getStatus());
        return appointment;
    }

    // Converts Appointment (Entity) to AppointmentDto
    public AppointmentDto toDto(Appointment appointment) {
        return new AppointmentDto(
                appointment.getAppointmentId(),
                appointment.getTitle(),
                appointment.getDescription(),
                appointment.getVideo(),
                appointment.getDoctor().getDoctorId(),
                appointment.getDoctor().getFirstName(),
                appointment.getDoctor().getLastName(),
                appointment.getPatient().getPatientId(),
                appointment.getPatient().getFirstName(),
                appointment.getPatient().getLastName(),
                appointment.getDuration(),
                appointment.getDate(),
                appointment.getStatus()
        );
    }
}


