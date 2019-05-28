package com.kakaopay.ecotourism.controller;

import com.kakaopay.ecotourism.model.EcoTourism;
import com.kakaopay.ecotourism.service.ProgramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/programs")
public class ProgramController {
    private final ProgramService programService;

    public ProgramController(final ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody EcoTourism ecoTourism){
        programService.createEcoTourism(ecoTourism);
        return ResponseEntity.ok().build();
    }
}
