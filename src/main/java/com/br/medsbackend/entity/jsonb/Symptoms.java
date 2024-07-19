package com.br.medsbackend.entity.jsonb;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
public class Symptoms {
    private String description;
    private String severity;
    private LocalDateTime onsetDate;
}
