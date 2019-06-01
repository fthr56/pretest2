package com.kakaopay.ecotourism.controller;

import com.kakaopay.ecotourism.model.EcoTourism;
import com.kakaopay.ecotourism.model.Program;
import com.kakaopay.ecotourism.request.RequestRegionProgram;
import com.kakaopay.ecotourism.response.ResponseEcoTourism;
import com.kakaopay.ecotourism.response.ResponseRegionProgram;
import com.kakaopay.ecotourism.service.ProgramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/programs")
public class ProgramController {
    private final ProgramService programService;

    public ProgramController(final ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping("/{id}")
    public ResponseEntity search(@PathVariable String id) {
        List<Program> programs = programService.findProgramByRegionId(id);

        List<ResponseEcoTourism> responseEcoTourisms = new ArrayList<>();
        for (Program program : programs) {
            ResponseEcoTourism responseEcoTourism = ResponseEcoTourism.builder()
                    .program(program).build();
            responseEcoTourisms.add(responseEcoTourism);
        }

        return ResponseEntity.ok(responseEcoTourisms);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody EcoTourism ecoTourism) {
        Program program = programService.createEcoTourism(ecoTourism);
        return ResponseEntity.ok(program);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable String id, @RequestBody EcoTourism ecoTourism) {
        Program program = programService.update(id, ecoTourism);
        return ResponseEntity.ok(program);
    }

    @GetMapping("/regions")
    public ResponseEntity searchRegionProgram(RequestRegionProgram requestRegionProgram) {
        String regionName = requestRegionProgram.getRegion();
        List<Program> programs = programService.findProgramByRegionName(regionName);
        ResponseRegionProgram regionProgram = ResponseRegionProgram.programsToRegionProgram(regionName, programs);
        return ResponseEntity.ok(regionProgram);
    }
}
