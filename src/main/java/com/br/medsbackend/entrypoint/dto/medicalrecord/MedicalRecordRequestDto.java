package com.br.medsbackend.entrypoint.dto.medicalrecord;

import com.br.medsbackend.core.enumeration.Gender;
import com.br.medsbackend.core.enumeration.Status;

import java.util.Date;

public record MedicalRecordRequestDto(
        String patientName,
        String patientSurname,
        Gender patientGender,
        Date birthDate,
        Date admissionDate,
        String admissionReason,
        String comorbidities,
        String CID,
        int bed,
        Status patientStatus) {
}
