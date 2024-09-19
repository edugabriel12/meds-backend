package com.br.medsbackend.entrypoint.dto.medicalrecord;

import com.br.medsbackend.core.enumeration.Status;
import com.br.medsbackend.dataprovider.entity.MedicalRecordEntity;

import java.util.Date;
import java.util.UUID;

public record GetMedicalRecordDto(
        UUID id,
        String patientFullName,
        String patientGender,
        Date birthDate,
        Date admissionDate,
        String admissionReason,
        String comorbidities,
        String Cid,
        int bed,
        Status patientStatus) {

    public static GetMedicalRecordDto fromEntity(MedicalRecordEntity entity) {
        return new GetMedicalRecordDto(
                entity.getId(),
                entity.getPatient().getFullName(),
                entity.getPatient().getGender().getName(),
                entity.getPatient().getBirthDate(),
                entity.getAdmissionDate(),
                entity.getAdmissionReason(),
                entity.getComorbidities(),
                entity.getCid(),
                entity.getBed(),
                entity.getPatientStatus()
        );
    }
}
