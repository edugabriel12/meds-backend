package com.br.medsbackend.service;

import com.br.medsbackend.dto.patient.GetPatientDto;
import com.br.medsbackend.dto.patient.PatientRequestDto;
import com.br.medsbackend.entity.PatientEntity;
import com.br.medsbackend.exception.AlreadyExistsException;
import com.br.medsbackend.exception.NotFoundException;
import com.br.medsbackend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository repository;

    @Autowired
    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public void checkIfPatientExistsBySusCardNumber(String susCardNumber) {
        if (repository.findBySusCardNumber(susCardNumber).isPresent()) {
            throw new AlreadyExistsException("Patient", "susCardNumber", susCardNumber);
        }
    }

    public PatientEntity registerPatient(PatientRequestDto request) {
        return repository.save(PatientEntity.fromRequest(request));
    }

    public PatientEntity findPatientBySusCardNumber(String susCardNumber) {
        return repository.findBySusCardNumber(susCardNumber)
                .orElseThrow(() -> new NotFoundException("Patient", susCardNumber));
    }

    public Page<GetPatientDto> getPatients(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(GetPatientDto::fromEntity);
    }
}
