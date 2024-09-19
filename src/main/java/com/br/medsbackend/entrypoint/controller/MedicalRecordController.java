package com.br.medsbackend.entrypoint.controller;

import com.br.medsbackend.core.usecase.MedicalRecordService;
import com.br.medsbackend.core.usecase.PatientService;
import com.br.medsbackend.entrypoint.dto.medicalrecord.GetMedicalRecordDto;
import com.br.medsbackend.entrypoint.dto.medicalrecord.MedicalRecordRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/records")
public class MedicalRecordController {

    private final MedicalRecordService service;

    private final PatientService patientService;

    @Autowired
    public MedicalRecordController(MedicalRecordService service, PatientService patientService) {
        this.service = service;
        this.patientService = patientService;
    }

    @PostMapping("/{doctorId}")
    public void registerPatientMedicalRecord(
            @PathVariable(name = "doctorId") UUID doctorId,
            @RequestBody MedicalRecordRequestDto medicalRecordRequest) {
        final var patientFullName = medicalRecordRequest.patientName() + " " + medicalRecordRequest.patientSurname();

        patientService.checkIfPatientExistsByFullName(patientFullName);

        final var patientEntity = patientService.registerPatient(medicalRecordRequest, doctorId);

        service.registerMedicalRecord(patientEntity, medicalRecordRequest);
    }

    @GetMapping
    public ResponseEntity<GetMedicalRecordDto> getMedicalRecordByPatientFullName(@RequestParam("fullName") String fullName) {
        final var patient = patientService.findPatientByFullName(fullName);

        final var medicalRecord = service.getMedicalRecordByPatient(patient);

        return ResponseEntity.ok(GetMedicalRecordDto.fromEntity(medicalRecord));
    }

    @GetMapping("/search")
    public ResponseEntity<List<GetMedicalRecordDto>> searchMedicalRecords(@RequestParam("inputName") String inputName) {
        final var patient = patientService.searchPatients(inputName);

        final var medicalRecords = service.searchMedicalRecords(patient);

        return ResponseEntity.ok(medicalRecords);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<Page<GetMedicalRecordDto>> getMedicalRecordsByDoctor(
            @PathVariable(name = "doctorId") UUID doctorId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ){
        Pageable pageable = PageRequest.of(page, limit);
        return ResponseEntity.ok(service.getMedicalRecordsByDoctor(doctorId, pageable));
    }
}
