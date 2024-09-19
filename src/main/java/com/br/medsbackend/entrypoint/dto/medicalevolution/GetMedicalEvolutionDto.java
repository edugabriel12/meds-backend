package com.br.medsbackend.entrypoint.dto.medicalevolution;

import com.br.medsbackend.dataprovider.entity.MedicalEvolutionEntity;

import java.time.LocalDateTime;

public record GetMedicalEvolutionDto(String actualState, LocalDateTime date) {

    public static GetMedicalEvolutionDto fromEntity(MedicalEvolutionEntity entity) {
        return new GetMedicalEvolutionDto(
                entity.getActualState(),
                entity.getDate()
        );
    }
}
