package com.br.medsbackend.core.usecase;

import com.br.medsbackend.core.exception.NotFoundException;
import com.br.medsbackend.dataprovider.entity.MedicalRecordEntity;
import com.br.medsbackend.dataprovider.entity.PatientEntity;
import com.br.medsbackend.dataprovider.repository.MedicalRecordRepository;
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
public class MedicalRecordService {

    private final MedicalRecordRepository repository;

    @Autowired
    public MedicalRecordService(MedicalRecordRepository repository) {
        this.repository = repository;
    }

    public MedicalRecordEntity findMedicalRecord(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Medical Record", id.toString()));
    }

    public void registerMedicalRecord(PatientEntity patient, MedicalRecordRequestDto request) {
        repository.save(MedicalRecordEntity.fromRequest(patient, request));
    }

    public MedicalRecordEntity getMedicalRecordByPatient(PatientEntity patient) {
        return repository.findMedicalRecordEntityByPatient(patient)
                .orElseThrow(() -> new NotFoundException("Medical Record", patient.getId().toString()));
    }

    public List<GetMedicalRecordDto> searchMedicalRecords(List<PatientEntity> patients) {
        List<GetMedicalRecordDto> medicalRecords = new ArrayList<>();

        for (PatientEntity patient : patients) {
            final var medicalRecord = getMedicalRecordByPatient(patient);
            medicalRecords.add(GetMedicalRecordDto.fromEntity(medicalRecord));
        }

        return medicalRecords;
    }

    public Page<GetMedicalRecordDto> getMedicalRecordsByDoctor(UUID doctorId, Pageable pageable) {
        return repository.findByDoctorId(doctorId, pageable)
                .map(GetMedicalRecordDto::fromEntity);
    }


}
