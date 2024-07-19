package com.br.medsbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "enderecos")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne(mappedBy = "address")
    private PatientEntity patient;

    @Column(name = "rua", nullable = false)
    private String street;

    @Column(name = "bairro", nullable = false)
    private String district;

    @Column(name = "numero", nullable = false)
    private String number;

    @Column(name = "complemento")
    private String complement;

    @Column(name = "cep", nullable = false)
    private String postCode;

    @Column(name = "cidade", nullable = false)
    private String city;

    @Column(name = "estado", nullable = false)
    private String state;

    @Column(name = "pais", nullable = false)
    private String country;
}
