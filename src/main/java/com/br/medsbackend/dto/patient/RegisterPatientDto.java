package com.br.medsbackend.dto.patient;

import com.br.medsbackend.entity.PatientEntity;

public record RegisterPatientDto (String susCardNumber, String name, String surname){

    public static RegisterPatientDto fromEntity(PatientEntity entity) {
        return new RegisterPatientDto(
            entity.getSusCardNumber(),
            entity.getName(),
            entity.getSurname()
        );
    }
}
