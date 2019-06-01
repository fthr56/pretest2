package com.kakaopay.ecotourism.service;

import com.kakaopay.ecotourism.model.*;
import com.kakaopay.ecotourism.repository.ProgramRepository;
import jdk.nashorn.internal.runtime.events.RecompilationEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;
    private final RegionService regionService;
    private final ThemeService themeService;

    public ProgramService(final ProgramRepository programRepository, final RegionService regionService, final ThemeService themeService) {
        this.programRepository = programRepository;
        this.regionService = regionService;
        this.themeService = themeService;
    }

    @Transactional
    public Program create(final Program program) {
        return programRepository.save(program);
    }

    @Transactional
    public Program createEcoTourism(EcoTourism ecoTourism) {
        List<Region> regions = ecoTourism.toRegions();
        List<Theme> themes = ecoTourism.toThemes();

        regions = regionService.create(regions);
        themes = themeService.create(themes);

        Program program = ecoTourism.toProgram(regions, themes);
        return create(program);
    }

    public Program update(final String id, final EcoTourism ecoTourism) {
        Program program = programRepository.findById(id).get();
        program.update(ecoTourism);
        return create(program);
    }

    public List<Program> findProgramByRegionId(final String id) {
        Region region = regionService.findByRegionId(id);
        return region.getPrograms();
    }

    public List<Program> findProgramByRegionName(final String regionName) {
        Region region = regionService.findByregionName(regionName);
        return region.getPrograms();
    }

    public List<Program> searchDescriptionContainKeyword(String keyword) {
        List<Program> programs = programRepository.findAll();

        List<Program> keywordPrograms = programs.stream()
                .filter(a -> a.descriptionContain(keyword))
                .collect(Collectors.toList());

        return keywordPrograms;
    }

    public int countDetailDescriptionContainKeyword(final String keyword) {
        List<Program> programs = programRepository.findAll();
        int count = programs.stream()
                .mapToInt(a -> a.countDetailDescriptionContainKeyword(keyword))
                .sum();
        return count;
    }

    public Recommend recommend(final String regionName, final String keyword) {
        List<Program> programs = findProgramByRegionName(regionName);
        List<Program> recommendPrograms = programs.stream().filter(a -> a.containRecommendKeyword(keyword)).collect(Collectors.toList());

        List<Recommend> recommends = recommendPrograms.stream().map(Program::toRecommend).collect(Collectors.toList());
        Recommend recommend = recommends.stream().max((a, b) -> a.compareRecommendPoint(keyword, b)).get();

        return recommend;
    }
}
