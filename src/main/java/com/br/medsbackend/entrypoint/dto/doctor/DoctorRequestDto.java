package com.br.medsbackend.entrypoint.dto.doctor;

import java.time.LocalDateTime;

public record DoctorRequestDto (
        String email,
        String password,
        String document,
        String name,
        String surname,
        LocalDateTime birthDate,
        String crm,
        String crmState,
        String specialty,
        String phone){
}
