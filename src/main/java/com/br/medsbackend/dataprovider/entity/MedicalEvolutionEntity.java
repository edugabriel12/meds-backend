package com.br.medsbackend.dataprovider.entity;

import com.br.medsbackend.dataprovider.entity.jsonb.VitalSigns;
import com.br.medsbackend.entrypoint.dto.medicalevolution.MedicalEvolutionRequestDto;
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

    @Column(name = "estado_atual")
    private String actualState;

    @Column(name = "tratamento_vigente")
    private String actualTreatment;

    @Column(name = "exames_laboratoriais")
    private String labExams;

    @Column(name = "pendencias")
    private String pendingIssues;

    @ManyToOne
    @JoinColumn(name = "prontuario_id", referencedColumnName = "id")
    private MedicalRecordEntity medicalRecord;

    @Column(name = "sinais_vitais")
    @JdbcTypeCode(SqlTypes.JSON)
    private VitalSigns vitalSigns;

    public static MedicalEvolutionEntity fromRequest(MedicalRecordEntity medicalRecord, MedicalEvolutionRequestDto request) {
        return MedicalEvolutionEntity.builder()
                .date(LocalDateTime.now())
                .actualState(request.actualState())
                .actualTreatment(request.actualTreatment())
                .labExams(request.labExams())
                .pendingIssues(request.pendingIssues())
                .medicalRecord(medicalRecord)
                .build();
    }
}
