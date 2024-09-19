package com.br.medsbackend.entrypoint.dto.doctor;

import com.br.medsbackend.dataprovider.entity.DoctorEntity;

import java.time.LocalDateTime;
import java.util.Date;

public record GetDoctorDto(
        String document,
        String name,
        String surname,
        Date birthDate,
        String crm,
        String crmState,
        String specialty,
        String phone) {

    public static GetDoctorDto fromEntity(DoctorEntity entity) {
        return new GetDoctorDto(
                entity.getDocument(),
                entity.getName(),
                entity.getSurname(),
                entity.getBirthDate(),
                entity.getCrm(),
                entity.getCrmState(),
                entity.getSpecialty(),
                entity.getPhone()
        );
    }
}
