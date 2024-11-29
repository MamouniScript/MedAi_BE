package ma.exovate.medaibe.controllers;


import ma.exovate.medaibe.dtos.Patient.PatientDTO;
import ma.exovate.medaibe.dtos.Patient.PatientReq;
import ma.exovate.medaibe.entities.City;
import ma.exovate.medaibe.entities.Patient;
import ma.exovate.medaibe.mappers.PatientMapper;
import ma.exovate.medaibe.repositories.CityRepo;
import ma.exovate.medaibe.services.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private CityRepo cityRepository;

    // Ajouter un patient
    @PostMapping
    public ResponseEntity<PatientDTO> addPatient(@RequestBody PatientReq patientReq) {
        City city = cityRepository.findById(patientReq.getCityId())
                .orElseThrow(() -> new IllegalArgumentException("City not found"));

        Patient patient = PatientMapper.toEntity(patientReq, city);
        Patient savedPatient = patientService.addPatient(patient);
        return ResponseEntity.ok(PatientMapper.toDTO(savedPatient));
    }

    // Récupérer un patient par son ID
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(PatientMapper.toDTO(patient));
    }

    // Récupérer tous les patients
    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients()
                .stream()
                .map(PatientMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(patients);
    }

    // Mettre à jour un patient
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody PatientReq patientReq) {
        City city = cityRepository.findById(patientReq.getCityId())
                .orElseThrow(() -> new IllegalArgumentException("City not found"));

        Patient patientToUpdate = PatientMapper.toEntity(patientReq, city);
        Patient updatedPatient = patientService.updatePatient(id, patientToUpdate);
        return ResponseEntity.ok(PatientMapper.toDTO(updatedPatient));
    }

    // Supprimer un patient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
