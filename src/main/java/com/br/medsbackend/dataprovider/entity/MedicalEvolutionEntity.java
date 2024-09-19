package com.br.medsbackend.dataprovider.entity;

import com.br.medsbackend.dataprovider.entity.jsonb.VitalSigns;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

    @Column(name = "observacoes", nullable = false)
    private String observations;

    @ManyToOne
    @JoinColumn(name = "prontuario_id", referencedColumnName = "id")
    private MedicalRecordEntity medicalRecord;

    @Column(name = "sinais_vitais")
    @JdbcTypeCode(SqlTypes.JSON)
    private VitalSigns vitalSigns;
}
