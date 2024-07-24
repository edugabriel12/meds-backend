package com.br.medsbackend.controller;

import com.br.medsbackend.dto.patient.GetPatientDto;
import com.br.medsbackend.dto.patient.PatientRequestDto;
import com.br.medsbackend.dto.patient.RegisterPatientDto;
import com.br.medsbackend.entity.PatientEntity;
import com.br.medsbackend.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;

    @Autowired
    public PatientController(PatientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RegisterPatientDto> registerPatient(@RequestBody PatientRequestDto patientRequest) {
        service.checkIfPatientExistsBySusCardNumber(patientRequest.susCardNumber());

        PatientEntity entity = service.registerPatient(patientRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{susCardNumber}")
                .buildAndExpand(entity.getSusCardNumber()).toUri();

        return ResponseEntity.created(location).body(RegisterPatientDto.fromEntity(entity));
    }

    @GetMapping("/{susCardNumber}")
    public ResponseEntity<GetPatientDto> getPatientBySusCardNumber(@PathVariable(name = "susCardNumber") String susCardNumber) {
        PatientEntity patient = service.findPatientBySusCardNumber(susCardNumber);

        return ResponseEntity.ok(GetPatientDto.fromEntity(patient));
    }
    @GetMapping
    public ResponseEntity<Page<GetPatientDto>> getPatients(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ){
        Pageable pageable = PageRequest.of(page, limit);
        return ResponseEntity.ok(service.getPatients(pageable));
    }
}
