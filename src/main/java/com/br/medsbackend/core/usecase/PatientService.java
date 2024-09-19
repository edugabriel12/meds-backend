package com.br.medsbackend.core.usecase;

import com.br.medsbackend.entrypoint.dto.patient.GetPatientDto;
import com.br.medsbackend.entrypoint.dto.patient.PatientRequestDto;
import com.br.medsbackend.dataprovider.entity.PatientEntity;
import com.br.medsbackend.core.exception.AlreadyExistsException;
import com.br.medsbackend.core.exception.NotFoundException;
import com.br.medsbackend.dataprovider.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public PatientEntity registerPatient(PatientRequestDto request, UUID doctorId) {
        final var doctor = doctorService.findDoctor(doctorId);

        return repository.save(PatientEntity.fromRequest(request, doctor));
    }

    public PatientEntity findPatientByFullName(String fullName) {
        return repository.findByFullName(fullName)
                .orElseThrow(() -> new NotFoundException("Patient", fullName));
    }

    public List<GetPatientDto> searchPatients(String inputName) {
        final var patients = repository.findByFullNameContainingInputName(inputName);

        if (patients.isEmpty()) {
            throw new NotFoundException("Patients", inputName);
        }

        return patients.stream().map(GetPatientDto::fromEntity).toList();
    }

    public Page<GetPatientDto> getPatientsByDoctor(UUID doctorId, Pageable pageable) {
        final var patients = repository.findByDoctorId(doctorId, pageable);

        if (patients.isEmpty()) {
            throw new NotFoundException("Patients", doctorId.toString());
        }

        return patients.map(GetPatientDto::fromEntity);
    }
}
