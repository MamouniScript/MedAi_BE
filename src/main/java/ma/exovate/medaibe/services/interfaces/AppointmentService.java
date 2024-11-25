package ma.exovate.medaibe.services.interfaces;


import ma.exovate.medaibe.dtos.AppointmentDto;
import ma.exovate.medaibe.dtos.AppointmentReq;

import java.util.List;

public interface AppointmentService {

    AppointmentDto getAppointmentById(Long appointmentId);

    List<AppointmentDto> getAllAppointments();

    AppointmentDto createAppointment(AppointmentReq req);

    AppointmentDto updateAppointment(Long appointmentId, AppointmentReq req);

    void deleteAppointment(Long appointmentId);
}


