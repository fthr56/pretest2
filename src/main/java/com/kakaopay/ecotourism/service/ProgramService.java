package com.kakaopay.ecotourism.service;

import com.kakaopay.ecotourism.model.EcoTourism;
import com.kakaopay.ecotourism.model.Program;
import com.kakaopay.ecotourism.model.Region;
import com.kakaopay.ecotourism.model.Theme;
import com.kakaopay.ecotourism.repository.ProgramRepository;
import com.kakaopay.ecotourism.repository.RegionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
