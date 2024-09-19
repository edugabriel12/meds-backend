package com.br.medsbackend.dataprovider.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admissao_id", referencedColumnName = "id")
    private AdmissionEntity admission;
}
