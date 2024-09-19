package com.br.medsbackend.entrypoint.dto.patient;

import com.br.medsbackend.dataprovider.entity.PatientEntity;

public record RegisterPatientDto (String fullName){

    public static RegisterPatientDto fromEntity(PatientEntity entity) {
        return new RegisterPatientDto(
            entity.getFullName()
        );
    }
}
