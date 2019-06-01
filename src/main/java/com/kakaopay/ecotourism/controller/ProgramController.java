package com.kakaopay.ecotourism.controller;

import com.kakaopay.ecotourism.model.EcoTourism;
import com.kakaopay.ecotourism.model.Program;
import com.kakaopay.ecotourism.model.Recommend;
import com.kakaopay.ecotourism.request.ReqKeyword;
import com.kakaopay.ecotourism.request.ReqRecommend;
import com.kakaopay.ecotourism.request.ReqRegionProgram;
import com.kakaopay.ecotourism.response.ResDescriptionProgram;
import com.kakaopay.ecotourism.response.ResDetailDescriptionKeyword;
import com.kakaopay.ecotourism.response.ResEcoTourism;
import com.kakaopay.ecotourism.response.ResRegionProgram;
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

        List<ResEcoTourism> resEcoTourisms = new ArrayList<>();
        for (Program program : programs) {
            ResEcoTourism resEcoTourism = ResEcoTourism.builder()
                    .program(program).build();
            resEcoTourisms.add(resEcoTourism);
        }

        return ResponseEntity.ok(resEcoTourisms);
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
    public ResponseEntity searchRegionProgram(ReqRegionProgram reqRegionProgram) {
        String regionName = reqRegionProgram.getRegion();
        List<Program> programs = programService.findProgramByRegionName(regionName);
        ResRegionProgram regionProgram = ResRegionProgram.programsToRegionProgram(regionName, programs);
        return ResponseEntity.ok(regionProgram);
    }

    @GetMapping("/descriptions")
    public ResponseEntity searchDescriptionKeyword(ReqKeyword descriptionKeyword) {
        String keyword = descriptionKeyword.getKeyword();
        List<Program> programs = programService.searchDescriptionContainKeyword(keyword);
        ResDescriptionProgram descriptionProgram = ResDescriptionProgram.programsToResponseDescriptionProgram(keyword, programs);
        return ResponseEntity.ok(descriptionProgram);
    }

    @GetMapping("/detail-descriptions")
    public ResponseEntity searchDetailDescriptionKeyword(ReqKeyword detailDescriptionkeyword) {
        String keyword = detailDescriptionkeyword.getKeyword();
        int count = programService.countDetailDescriptionContainKeyword(keyword);
        ResDetailDescriptionKeyword resBody = new ResDetailDescriptionKeyword(keyword, count);
        return ResponseEntity.ok(resBody);
    }

    @GetMapping("/recommends")
    public ResponseEntity recommendEcoTourism(ReqRecommend reqRecommend) {
        String regionName = reqRecommend.getRegion();
        String keyword = reqRecommend.getKeyword();
        Recommend recommend = programService.recommend(regionName, keyword);

        return ResponseEntity.ok(recommend.toResponseRecommend());
    }
}
