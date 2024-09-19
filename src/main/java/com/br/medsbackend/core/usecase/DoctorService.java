package com.br.medsbackend.core.usecase;

import com.br.medsbackend.entrypoint.dto.doctor.DoctorRequestDto;
import com.br.medsbackend.dataprovider.entity.DoctorEntity;
import com.br.medsbackend.core.exception.AlreadyExistsException;
import com.br.medsbackend.core.exception.NotFoundException;
import com.br.medsbackend.dataprovider.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DoctorService {

    private final DoctorRepository repository;

    @Autowired
    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }

    public DoctorEntity findDoctor(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Doctor", id.toString()));
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
