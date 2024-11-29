package ma.exovate.medaibe.services.interfaces;

import ma.exovate.medaibe.entities.Patient;

import java.util.List;

public interface PatientService {
    Patient getPatientById(Long id);
    List<Patient> getAllPatients();
    Patient addPatient(Patient patientReq);
    Patient updatePatient(Long id, Patient patientReq);
    void deletePatient(Long id);
}
