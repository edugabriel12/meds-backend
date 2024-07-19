package com.br.medsbackend.entity.jsonb;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FluidBalance {
    private Integer totalIntake;
    private Integer totalOutput;
    private Integer netBalance;
}
