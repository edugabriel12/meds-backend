package com.br.medsbackend.entrypoint.controller;

import com.br.medsbackend.core.usecase.MedicalEvolutionService;
import com.br.medsbackend.core.usecase.MedicalRecordService;
import com.br.medsbackend.entrypoint.dto.medicalevolution.GetMedicalEvolutionDto;
import com.br.medsbackend.entrypoint.dto.medicalevolution.MedicalEvolutionRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/evolutions")
public class MedicalEvolutionController {

    private final MedicalEvolutionService service;

    private final MedicalRecordService medicalRecordService;

    @Autowired
    public MedicalEvolutionController(MedicalEvolutionService service, MedicalRecordService medicalRecordService) {
        this.service = service;
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping("/{medicalRecordId}")
    public void registerMedicalEvolution(
            @PathVariable(name = "medicalRecordId") UUID medicalRecordId,
            @RequestBody MedicalEvolutionRequestDto medicalEvolutionRequest) {
        final var medicalRecord = medicalRecordService.findMedicalRecord(medicalRecordId);

       service.registerMedicalEvolution(medicalRecord, medicalEvolutionRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMedicalEvolutionDto> getMedicalEvolution(@PathVariable("id") UUID id) {
        final var medicalEvolution = service.findMedicalEvolution(id);

        return ResponseEntity.ok(GetMedicalEvolutionDto.fromEntity(medicalEvolution));
    }

    @GetMapping("/{medicalRecordId}")
    public ResponseEntity<Page<GetMedicalEvolutionDto>> getMedicalEvolutions(
            @PathVariable(name = "medicalRecordId") UUID medicalRecordId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ){
        Pageable pageable = PageRequest.of(page, limit);
        return ResponseEntity.ok(service.getMedicalEvolutions(medicalRecordId, pageable));
    }
}
