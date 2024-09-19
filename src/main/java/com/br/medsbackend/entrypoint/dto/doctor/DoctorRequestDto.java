package com.br.medsbackend.entrypoint.dto.doctor;

import java.util.Date;

public record DoctorRequestDto (
        String email,
        String password,
        String document,
        String name,
        String surname,
        Date birthDate,
        String crm,
        String crmState,
        String specialty,
        String phone){
}
