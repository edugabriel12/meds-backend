package com.br.medsbackend.entrypoint.controller;

import com.br.medsbackend.entrypoint.dto.doctor.GetDoctorDto;
import com.br.medsbackend.entrypoint.dto.doctor.RegisterDoctorDto;
import com.br.medsbackend.entrypoint.dto.doctor.DoctorRequestDto;
import com.br.medsbackend.dataprovider.entity.DoctorEntity;
import com.br.medsbackend.core.usecase.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService service;

    @Autowired
    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetDoctorDto> getDoctor(@PathVariable(name = "id") UUID id) {
        DoctorEntity doctor = service.findDoctor(id);

        return ResponseEntity.ok(GetDoctorDto.fromEntity(doctor));
    }

    @PostMapping
    public ResponseEntity<RegisterDoctorDto> registerDoctor(@RequestBody DoctorRequestDto doctorRequest) {
        service.checkIfDoctorExistsByEmail(doctorRequest.email());
        service.checkIfDoctorExistsByDocument(doctorRequest.document());

        DoctorEntity entity = service.registerDoctor(doctorRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{document}")
                .buildAndExpand(entity.getDocument()).toUri();

        return ResponseEntity.created(location).body(RegisterDoctorDto.fromEntity(entity));
    }
}
