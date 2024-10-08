package com.br.medsbackend.dataprovider.entity;

import com.br.medsbackend.entrypoint.dto.doctor.DoctorRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false, unique = true)
    private String password;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    @Column(name = "pacientes")
    @Transient
    private Set<PatientEntity> patients;

    @Column(name = "documento", nullable = false)
    private String document;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "sobrenome", nullable = false)
    private String surname;

    @Column(name = "data_nascimento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;

    @Column(name = "crm", nullable = false)
    private String crm;

    @Column(name = "estado_crm", nullable = false)
    private String crmState;

    @Column(name = "especialidade", nullable = false)
    private String specialty;

    @Column(name = "telefone", nullable = false)
    private String phone;

    public static DoctorEntity fromRequest(DoctorRequestDto request) {
        return DoctorEntity.builder()
                .document(request.document())
                .name(request.name())
                .surname(request.surname())
                .birthDate(request.birthDate())
                .crm(request.crm())
                .crmState(request.crmState())
                .specialty(request.specialty())
                .phone(request.phone())
                .build();
    }
}
