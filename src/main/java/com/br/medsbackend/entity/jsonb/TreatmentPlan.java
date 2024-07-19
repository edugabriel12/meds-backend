package com.br.medsbackend.entity.jsonb;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TreatmentPlan {
    private String medicationName;
    private String dosage;
    private String frequency;
}
