package com.br.medsbackend.dataprovider.entity;

import com.br.medsbackend.core.enumeration.Gender;
import com.br.medsbackend.entrypoint.dto.medicalrecord.MedicalRecordRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pacientes")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome_completo", nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gênero", nullable = false)
    private Gender gender;

    @Column(name = "data_nascimento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;

    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false, referencedColumnName = "id")
    @Transient
    @JsonIgnore
    private DoctorEntity doctor;

    public static PatientEntity fromRequest(MedicalRecordRequestDto request, DoctorEntity doctor) {
        return PatientEntity.builder()
                .fullName(request.patientName() + " " + request.patientSurname())
                .gender(request.patientGender())
                .birthDate(request.birthDate())
                .doctor(doctor)
                .build();
    }
}
