package com.br.medsbackend.dataprovider.entity;

import com.br.medsbackend.core.enumeration.Status;
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

    @Column(name = "data_internacao")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime admissionDate;

    @Column(name = "motivo_internacao")
    private String admissionReason;

    @Column(name = "leito")
    private int bed;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status patientStatus;

    @Column(name = "cid")
    private String cid;

    @Column(name = "comorbidades")
    private String comorbidities;
}
