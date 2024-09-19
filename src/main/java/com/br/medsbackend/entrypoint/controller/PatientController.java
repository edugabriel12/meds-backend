package com.br.medsbackend.entrypoint.controller;

import com.br.medsbackend.core.usecase.PatientService;
import com.br.medsbackend.dataprovider.entity.PatientEntity;
import com.br.medsbackend.entrypoint.dto.patient.GetPatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;

    @Autowired
    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<GetPatientDto> getPatientByFullName(@RequestParam("fullName") String fullName) {
        PatientEntity patient = service.findPatientByFullName(fullName);

        return ResponseEntity.ok(GetPatientDto.fromEntity(patient));
    }

    @GetMapping("/search")
    public ResponseEntity<List<GetPatientDto>> searchPatients(@RequestParam("inputName") String inputName) {
        List<GetPatientDto> patient = service.searchPatients(inputName);

        return ResponseEntity.ok(patient);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<Page<GetPatientDto>> getPatientsByDoctor(
            @PathVariable(name = "doctorId") UUID doctorId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ){
        Pageable pageable = PageRequest.of(page, limit);
        return ResponseEntity.ok(service.getPatientsByDoctor(doctorId, pageable));
    }
}
