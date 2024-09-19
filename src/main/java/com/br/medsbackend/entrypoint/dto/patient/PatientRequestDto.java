package com.br.medsbackend.entrypoint.dto.patient;

import com.br.medsbackend.core.enumeration.Gender;

import java.time.LocalDateTime;

public record PatientRequestDto(
        String fullName,
        Gender gender,
        LocalDateTime birthDate
) {
}
