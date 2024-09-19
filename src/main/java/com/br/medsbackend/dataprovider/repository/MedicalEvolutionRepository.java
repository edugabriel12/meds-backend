package com.br.medsbackend.dataprovider.repository;

import com.br.medsbackend.dataprovider.entity.MedicalEvolutionEntity;
import com.br.medsbackend.dataprovider.entity.MedicalRecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MedicalEvolutionRepository extends JpaRepository<MedicalEvolutionEntity, UUID> {

    Page<MedicalEvolutionEntity> findAllByMedicalRecord(MedicalRecordEntity medicalRecord, Pageable pageable);

}
