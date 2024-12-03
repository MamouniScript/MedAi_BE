package ma.exovate.medaibe.services;

import ma.exovate.medaibe.entities.Patient;
import ma.exovate.medaibe.repositories.PatientRepo;
import ma.exovate.medaibe.services.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepo patientRepository;

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient with ID " + id + " not found"));
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Long id, Patient patientReq) {
        Patient existingPatient = getPatientById(id);
        existingPatient.setFirstName(patientReq.getFirstName());
        existingPatient.setLastName(patientReq.getLastName());
        existingPatient.setEmail(patientReq.getEmail());
        existingPatient.setPassword(patientReq.getPassword());
        existingPatient.setCity(patientReq.getCity());
        return patientRepository.save(existingPatient);
    }

    @Override
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new IllegalArgumentException("Patient with ID " + id + " does not exist");
        }
        patientRepository.deleteById(id);
    }
}
