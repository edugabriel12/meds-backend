package com.br.medsbackend.entity;

import com.br.medsbackend.enumeration.Gender;
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
@Table(name = "pacientes")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false, unique = true)
    private String password;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "sobrenome", nullable = false)
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(name = "gênero", nullable = false)
    private Gender gender;

    @Column(name = "data_nascimento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime birthDate;

    @Column(name = "cartão_sus", nullable = false, unique = true)
    private String susCardNumber;

    @Column(name = "telefone", nullable = false)
    private String phone;

    @Column(name = "contato_emergência")
    private String emergencyPhone;

    @Column(name = "peso", nullable = false)
    private Double weight;

    @Column(name = "altura", nullable = false)
    private int height;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id", nullable = false)
    private AddressEntity address;

    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false, referencedColumnName = "id")
    private DoctorEntity doctor;
}
