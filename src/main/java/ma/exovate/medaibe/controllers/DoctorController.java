package ma.exovate.medaibe.controllers;



import ma.exovate.medaibe.dtos.Doctor.DoctorDTO;
import ma.exovate.medaibe.dtos.Doctor.DoctorReq;
import ma.exovate.medaibe.entities.City;
import ma.exovate.medaibe.entities.Doctor;
import ma.exovate.medaibe.entities.Speciality;
import ma.exovate.medaibe.mappers.DoctorMapper;
import ma.exovate.medaibe.repositories.CityRepo;
import ma.exovate.medaibe.repositories.SpecialityRepo;
import ma.exovate.medaibe.services.interfaces.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private CityRepo cityRepository;

    @Autowired
    private SpecialityRepo specialityRepository;

    // Ajouter un docteur
    @PostMapping
    public ResponseEntity<DoctorDTO> addDoctor(@RequestBody DoctorReq doctorReq) {
        City city = cityRepository.findById(doctorReq.getCityId())
                .orElseThrow(() -> new IllegalArgumentException("City not found"));
        Speciality specialization = specialityRepository.findById(doctorReq.getSpecializationId())
                .orElseThrow(() -> new IllegalArgumentException("Specialization not found"));

        Doctor doctor = DoctorMapper.toEntity(doctorReq, city, specialization);
        Doctor savedDoctor = doctorService.addDoctor(doctor);
        return ResponseEntity.ok(DoctorMapper.toDTO(savedDoctor));
    }

    // Récupérer un docteur par son ID
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(DoctorMapper.toDTO(doctor));
    }

    // Récupérer tous les docteurs
    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<DoctorDTO> doctors = doctorService.getAllDoctors()
                .stream()
                .map(DoctorMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(doctors);
    }

    // Mettre à jour un docteur
    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long id, @RequestBody DoctorReq doctorReq) {
        City city = cityRepository.findById(doctorReq.getCityId())
                .orElseThrow(() -> new IllegalArgumentException("City not found"));
        Speciality specialization = specialityRepository.findById(doctorReq.getSpecializationId())
                .orElseThrow(() -> new IllegalArgumentException("Specialization not found"));

        Doctor doctorToUpdate = DoctorMapper.toEntity(doctorReq, city, specialization);
        Doctor updatedDoctor = doctorService.updateDoctor(id, doctorToUpdate);
        return ResponseEntity.ok(DoctorMapper.toDTO(updatedDoctor));
    }

    // Supprimer un docteur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}

