package com.br.medsbackend.service;

import com.br.medsbackend.dto.doctor.DoctorRequestDto;
import com.br.medsbackend.entity.DoctorEntity;
import com.br.medsbackend.exception.AlreadyExistsException;
import com.br.medsbackend.exception.NotFoundException;
import com.br.medsbackend.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private final DoctorRepository repository;

    @Autowired
    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }

    public DoctorEntity findDoctorByDocument(String document) {
        return repository.findByDocument(document)
                .orElseThrow(() -> new NotFoundException("Doctor", document));
    }

    public void checkIfDoctorExistsByDocument(String document) {
        if (repository.findByDocument(document).isPresent()) {
            throw new AlreadyExistsException("Doctor", "document", document);
        }
    }

    public void checkIfDoctorExistsByEmail(String email) {
        if (repository.findByEmail(email).isPresent()) {
            throw new AlreadyExistsException("Doctor", "email", email);
        }
    }

    public DoctorEntity registerDoctor(DoctorRequestDto request) {
        return repository.save(DoctorEntity.fromRequest(request));
    }
}
