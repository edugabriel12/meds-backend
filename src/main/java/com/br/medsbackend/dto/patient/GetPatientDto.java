package com.br.medsbackend.dto.patient;

import com.br.medsbackend.entity.PatientEntity;
import com.br.medsbackend.enumeration.Gender;

import java.time.LocalDateTime;

public record GetPatientDto (
        String name,
        String surname,
        Gender gender,
        LocalDateTime birthDate,
        String susCardNumber,
        String phone,
        String emergencyPhone,
        Double weight,
        int height
) {
    public static GetPatientDto fromEntity(PatientEntity entity) {
        return new GetPatientDto(
                entity.getName(),
                entity.getSurname(),
                entity.getGender(),
                entity.getBirthDate(),
                entity.getSusCardNumber(),
                entity.getPhone(),
                entity.getEmergencyPhone(),
                entity.getWeight(),
                entity.getHeight()
        );
    }
}
