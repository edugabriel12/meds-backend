package com.br.medsbackend.dataprovider.entity.jsonb;

import lombok.*;

@Getter
@Builder
public class VitalSigns {
    private Integer heartRate;
    private Integer respiratoryRate;
    private String bloodPressure;
    private Float hgt;
    private Float temperature;
    private Integer oxygenSaturation;
    private Integer totalIntake;
    private Integer totalOutput;
    private Integer netBalance;
}
