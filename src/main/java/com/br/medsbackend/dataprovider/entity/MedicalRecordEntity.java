package com.br.medsbackend.dataprovider.entity;

import com.br.medsbackend.core.enumeration.Status;
import com.br.medsbackend.entrypoint.dto.medicalrecord.MedicalRecordRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "prontuarios")
public class MedicalRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    private PatientEntity patient;

    @OneToMany(mappedBy = "medicalRecord")
    private List<MedicalEvolutionEntity> medicalEvolutions;

    @Column(name = "data_internacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date admissionDate;

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

    public static MedicalRecordEntity fromRequest(PatientEntity patient, MedicalRecordRequestDto request) {
        return MedicalRecordEntity.builder()
                .patient(patient)
                .admissionDate(request.admissionDate())
                .admissionReason(request.admissionReason())
                .bed(request.bed())
                .patientStatus(request.patientStatus())
                .cid(request.CID())
                .comorbidities(request.comorbidities())
                .build();
    }
}
