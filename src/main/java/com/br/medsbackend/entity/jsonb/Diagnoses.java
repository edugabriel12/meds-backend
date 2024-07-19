package com.br.medsbackend.entity.jsonb;

import lombok.*;

@Getter
@Builder
public class Diagnoses {
    private String hypothesis;
    private String cid;
    private String comorbidities;
}
