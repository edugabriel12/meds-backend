package com.br.medsbackend.dto.patient;

import com.br.medsbackend.enumeration.Gender;

import java.time.LocalDateTime;

public record PatientRequestDto(
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
}
