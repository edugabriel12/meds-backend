package com.br.medsbackend.entrypoint.dto.medicalevolution;

public record MedicalEvolutionRequestDto(
        String actualState,
        String actualTreatment,
        String labExams,
        String pendingIssues) {
}
