package com.br.medsbackend.entity;

import com.br.medsbackend.entity.jsonb.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "evolucoes_medicas")
public class MedicalEvolutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "prontuario_id", referencedColumnName = "id")
    private MedicalRecordEntity medicalRecord;

    @Column(name = "observacoes", nullable = false)
    private String observations;

    @Column(name = "sinais_vitais")
    @JdbcTypeCode(SqlTypes.JSON)
    private VitalSigns vitalSigns;

    @Column(name = "sintomas")
    @JdbcTypeCode(SqlTypes.JSON)
    private Symptoms symptoms;

    @Column(name = "diagnosticos")
    @JdbcTypeCode(SqlTypes.JSON)
    private Diagnoses diagnoses;

    @Column(name = "balanco_hidrico")
    @JdbcTypeCode(SqlTypes.JSON)
    private FluidBalance fluidBalance;

    @Column(name = "plano_tratamento")
    @JdbcTypeCode(SqlTypes.JSON)
    private TreatmentPlan treatmentPlan;
}
