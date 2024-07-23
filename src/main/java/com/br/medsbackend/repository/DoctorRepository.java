package com.br.medsbackend.repository;

import com.br.medsbackend.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, UUID> {

    Optional<DoctorEntity> findByDocument(String document);

    Optional<DoctorEntity> findByEmail(String email);
}
