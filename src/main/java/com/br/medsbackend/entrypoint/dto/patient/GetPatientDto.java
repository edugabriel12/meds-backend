package com.br.medsbackend.entrypoint.dto.patient;

import com.br.medsbackend.dataprovider.entity.PatientEntity;

import java.util.UUID;

public record GetPatientDto (
        UUID patientId
) {
    public static GetPatientDto fromEntity(PatientEntity entity) {
        return new GetPatientDto(
                entity.getId()
        );
    }
}
