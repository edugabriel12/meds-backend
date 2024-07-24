package com.br.medsbackend.controller;

import com.br.medsbackend.dto.doctor.GetDoctorDto;
import com.br.medsbackend.dto.doctor.RegisterDoctorDto;
import com.br.medsbackend.dto.doctor.DoctorRequestDto;
import com.br.medsbackend.entity.DoctorEntity;
import com.br.medsbackend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService service;

    @Autowired
    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @GetMapping("/{document}")
    public ResponseEntity<GetDoctorDto> getDoctorByDocument(@PathVariable(name = "document") String document) {
        DoctorEntity doctor = service.findDoctorByDocument(document);

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
