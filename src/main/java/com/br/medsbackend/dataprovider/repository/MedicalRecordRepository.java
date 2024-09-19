package com.br.medsbackend.dataprovider.repository;

import com.br.medsbackend.dataprovider.entity.MedicalRecordEntity;
import com.br.medsbackend.dataprovider.entity.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecordEntity, UUID> {

    Optional<MedicalRecordEntity> findMedicalRecordEntityByPatient(PatientEntity patient);

    @Query("SELECT mr FROM MedicalRecordEntity mr JOIN mr.patient p WHERE p.doctor.id = :doctorId")
    Page<MedicalRecordEntity> findByDoctorId(@Param("doctorId") UUID doctorId, Pageable pageable);

}
