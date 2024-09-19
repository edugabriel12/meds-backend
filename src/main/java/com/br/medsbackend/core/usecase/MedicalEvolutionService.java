package com.br.medsbackend.core.usecase;

import com.br.medsbackend.core.exception.NotFoundException;
import com.br.medsbackend.dataprovider.entity.MedicalEvolutionEntity;
import com.br.medsbackend.dataprovider.entity.MedicalRecordEntity;
import com.br.medsbackend.dataprovider.entity.PatientEntity;
import com.br.medsbackend.dataprovider.repository.MedicalEvolutionRepository;
import com.br.medsbackend.dataprovider.repository.MedicalRecordRepository;
import com.br.medsbackend.entrypoint.dto.medicalevolution.GetMedicalEvolutionDto;
import com.br.medsbackend.entrypoint.dto.medicalevolution.MedicalEvolutionRequestDto;
import com.br.medsbackend.entrypoint.dto.medicalrecord.GetMedicalRecordDto;
import com.br.medsbackend.entrypoint.dto.medicalrecord.MedicalRecordRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MedicalEvolutionService {

    private final MedicalEvolutionRepository repository;

    private final MedicalRecordService medicalRecordService;

    @Autowired
    public MedicalEvolutionService(MedicalEvolutionRepository repository, MedicalRecordService medicalRecordService) {
        this.repository = repository;
        this.medicalRecordService = medicalRecordService;
    }

    public void registerMedicalEvolution(MedicalRecordEntity medicalRecord, MedicalEvolutionRequestDto request) {
        repository.save(MedicalEvolutionEntity.fromRequest(medicalRecord, request));
    }

    public MedicalEvolutionEntity findMedicalEvolution(UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Medical Evolution", id.toString()));
    }

    public Page<GetMedicalEvolutionDto> getMedicalEvolutions(UUID medicalRecordId, Pageable pageable) {
        final var medicalRecord = medicalRecordService.findMedicalRecord(medicalRecordId);

        return repository.findAllByMedicalRecord(medicalRecord, pageable)
                .map(GetMedicalEvolutionDto::fromEntity);
    }

}
