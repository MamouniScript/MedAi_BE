package ma.exovate.medaibe.services.interfaces;

import ma.exovate.medaibe.entities.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor getDoctorById(Long id);
    List<Doctor> getAllDoctors();
    Doctor addDoctor(Doctor doctorReq);
    Doctor updateDoctor(Long id, Doctor doctorReq);
    void deleteDoctor(Long id);
}
