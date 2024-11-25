package ma.exovate.medaibe.services;

import ma.exovate.medaibe.dtos.AppointmentDto;
import ma.exovate.medaibe.dtos.AppointmentReq;
import ma.exovate.medaibe.entities.Appointment;
import ma.exovate.medaibe.entities.Doctor;
import ma.exovate.medaibe.entities.Patient;
import ma.exovate.medaibe.exceptions.ResourceNotFoundException;
import ma.exovate.medaibe.mappers.AppointmentMapper;
import ma.exovate.medaibe.repositories.AppointmentRepo;
import ma.exovate.medaibe.repositories.DoctorRepo;
import ma.exovate.medaibe.repositories.PatientRepo;
import ma.exovate.medaibe.services.interfaces.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;
    private final AppointmentMapper mapper;

    public AppointmentServiceImpl(AppointmentRepo appointmentRepo, DoctorRepo doctorRepo, PatientRepo patientRepo, AppointmentMapper mapper) {
        this.appointmentRepo = appointmentRepo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
        this.mapper = mapper;
    }

    @Override
    public AppointmentDto getAppointmentById(Long appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + appointmentId));
        return mapper.toDto(appointment);
    }

    @Override
    public List<AppointmentDto> getAllAppointments() {
        return appointmentRepo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDto createAppointment(AppointmentReq req) {
        // Validate input
        validateAppointmentRequest(req);

        // Fetch related entities
        Doctor doctor = doctorRepo.findById(req.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + req.getDoctorId()));

        Patient patient = patientRepo.findById(req.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + req.getPatientId()));

        // Map request to entity
        Appointment appointment = mapper.repToEntity(req, doctor, patient);
        Appointment savedAppointment = appointmentRepo.save(appointment);

        return mapper.toDto(savedAppointment);
    }

    @Override
    public AppointmentDto updateAppointment(Long appointmentId, AppointmentReq req) {
        // Validate input
        validateAppointmentRequest(req);

        // Fetch existing appointment
        Appointment existingAppointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + appointmentId));

        // Fetch related entities
        Doctor doctor = doctorRepo.findById(req.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + req.getDoctorId()));

        Patient patient = patientRepo.findById(req.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + req.getPatientId()));

        // Update entity fields
        existingAppointment.setTitle(req.getTitle());
        existingAppointment.setDescription(req.getDescription());
        existingAppointment.setDoctor(doctor);
        existingAppointment.setPatient(patient);
        existingAppointment.setDate(req.getDate());
        existingAppointment.setStatus(req.getStatus());

        Appointment updatedAppointment = appointmentRepo.save(existingAppointment);
        return mapper.toDto(updatedAppointment);
    }

    @Override
    public void deleteAppointment(Long appointmentId) {
        if (!appointmentRepo.existsById(appointmentId)) {
            throw new ResourceNotFoundException("Appointment not found with ID: " + appointmentId);
        }
        appointmentRepo.deleteById(appointmentId);
    }

    // Utility method to validate AppointmentReq
    private void validateAppointmentRequest(AppointmentReq req) {
        if (req.getTitle() == null || req.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Appointment title cannot be null or empty.");
        }
        if (req.getDescription() == null || req.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Appointment description cannot be null or empty.");
        }
        if (req.getDate() == null) {
            throw new IllegalArgumentException("Appointment date cannot be null.");
        }
    }
}
