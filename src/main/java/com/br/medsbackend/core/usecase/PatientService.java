package com.br.medsbackend.core.usecase;

import com.br.medsbackend.core.exception.AlreadyExistsException;
import com.br.medsbackend.core.exception.NotFoundException;
import com.br.medsbackend.dataprovider.entity.PatientEntity;
import com.br.medsbackend.dataprovider.repository.PatientRepository;
import com.br.medsbackend.entrypoint.dto.medicalrecord.MedicalRecordRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository repository;

    private final DoctorService doctorService;

    @Autowired
    public PatientService(PatientRepository repository, DoctorService doctorService) {
        this.repository = repository;
        this.doctorService = doctorService;
    }

    public void checkIfPatientExistsByFullName(String fullName) {
        if (repository.findByFullName(fullName).isPresent()) {
            throw new AlreadyExistsException("Patient", "fullName", fullName);
        }
    }

    public PatientEntity registerPatient(MedicalRecordRequestDto request, UUID doctorId) {
        final var doctor = doctorService.findDoctor(doctorId);

        return repository.save(PatientEntity.fromRequest(request, doctor));
    }

    public PatientEntity findPatientByFullName(String fullName) {
        return repository.findByFullName(fullName)
                .orElseThrow(() -> new NotFoundException("Patient", fullName));
    }

    public List<PatientEntity> searchPatients(String inputName) {
        final var patients = repository.findByFullNameContainingInputName(inputName);

        if (patients.isEmpty()) {
            throw new NotFoundException("Patients", inputName);
        }

        return patients;
    }
}
