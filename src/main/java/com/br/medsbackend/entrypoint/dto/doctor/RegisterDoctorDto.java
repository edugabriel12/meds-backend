package com.br.medsbackend.entrypoint.dto.doctor;

import com.br.medsbackend.dataprovider.entity.DoctorEntity;

public record RegisterDoctorDto(String document, String name, String surname) {

    public static RegisterDoctorDto fromEntity(DoctorEntity entity) {
        return new RegisterDoctorDto(
            entity.getDocument(),
            entity.getName(),
            entity.getSurname()
        );
    }
}
