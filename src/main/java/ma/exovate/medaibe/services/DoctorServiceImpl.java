package ma.exovate.medaibe.services;

import ma.exovate.medaibe.entities.Doctor;
import ma.exovate.medaibe.repositories.DoctorRepo;
import ma.exovate.medaibe.services.interfaces.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepo doctorRepository;

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Doctor with ID " + id + " not found"));
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctorReq) {
        Doctor existingDoctor = getDoctorById(id);
        existingDoctor.setFirstName(doctorReq.getFirstName());
        existingDoctor.setLastName(doctorReq.getLastName());
        existingDoctor.setGender(doctorReq.getGender());
        existingDoctor.setPhone_Number(doctorReq.getPhone_Number());
        existingDoctor.setCin(doctorReq.getCin());
        existingDoctor.setBirthdate(doctorReq.getBirthdate());
        existingDoctor.setBirth_Place(doctorReq.getBirth_Place());
        existingDoctor.setEmail(doctorReq.getEmail());
        existingDoctor.setPassword(doctorReq.getPassword());
        existingDoctor.setPitch(doctorReq.getPitch());
        existingDoctor.setGraduation_year(doctorReq.getGraduation_year());
        existingDoctor.setVerification(doctorReq.getVerification());
        existingDoctor.setDocuments(doctorReq.getDocuments());
        existingDoctor.setBalance(doctorReq.getBalance());
        existingDoctor.setCommission(doctorReq.getCommission());
        existingDoctor.setCity(doctorReq.getCity());
        existingDoctor.setSpecialization(doctorReq.getSpecialization());
        existingDoctor.setAverageRating(doctorReq.getAverageRating());
        existingDoctor.setTotalReviews(doctorReq.getTotalReviews());
        existingDoctor.setAppointmentCount(doctorReq.getAppointmentCount());
        return doctorRepository.save(existingDoctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new IllegalArgumentException("Doctor with ID " + id + " does not exist");
        }
        doctorRepository.deleteById(id);
    }
}
