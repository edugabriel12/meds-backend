package com.br.medsbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "medicos")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    @Column(name = "pacientes")
    private Set<PatientEntity> patients;

    @Column(name = "id_usuario_interno")
    private Long internalUserId;

    @Column(name = "documento", nullable = false)
    private String document;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "sobrenome", nullable = false)
    private String surname;

    @Column(name = "data_nascimento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime birthDate;

    @Column(name = "crm", nullable = false)
    private String crm;

    @Column(name = "estado_crm", nullable = false)
    private String crmState;

    @Column(name = "especialidade", nullable = false)
    private String specialty;

    @Column(name = "telefone", nullable = false)
    private String phone;
}
