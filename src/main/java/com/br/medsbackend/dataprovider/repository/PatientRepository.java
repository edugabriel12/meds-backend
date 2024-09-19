package com.br.medsbackend.dataprovider.repository;

import com.br.medsbackend.dataprovider.entity.DoctorEntity;
import com.br.medsbackend.dataprovider.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, UUID> {

    Optional<PatientEntity> findByFullName(String fullName);

    List<PatientEntity> findAllByDoctor(DoctorEntity doctor);

    @Query("SELECT p FROM PatientEntity p WHERE p.fullName LIKE %:inputName%")
    List<PatientEntity> findByFullNameContainingInputName(@Param("inputName") String inputName);
}
