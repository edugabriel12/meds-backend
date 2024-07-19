package com.br.medsbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "admissões")
public class AdmissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "prontuario_id", referencedColumnName = "id")
    private MedicalRecordEntity medicalRecord;

    @Column(name = "data_internacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime admissionDate;

    @Column(name = "motivo_internacao", nullable = false)
    private String admissionReason;

    @Column(name = "comorbidades", nullable = false)
    private String comorbidities;
}
