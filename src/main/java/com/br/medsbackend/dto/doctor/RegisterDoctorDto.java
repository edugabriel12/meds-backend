package com.br.medsbackend.dto.doctor;

import com.br.medsbackend.entity.DoctorEntity;

public record RegisterDoctorDto(String document, String name, String surname) {

    public static RegisterDoctorDto fromEntity(DoctorEntity entity) {
        return new RegisterDoctorDto(
            entity.getDocument(),
            entity.getName(),
            entity.getSurname()
        );
    }
}
