package com.kakaopay.ecotourism.service;

import com.kakaopay.ecotourism.model.Program;
import com.kakaopay.ecotourism.model.Region;
import com.kakaopay.ecotourism.model.Theme;
import com.kakaopay.ecotourism.model.EcoTourism;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    private final Logger logger = LoggerFactory.getLogger(FileService.class);
    private final ProgramService programService;
    private final RegionService regionService;
    private final ThemeService themeService;

    public FileService(final ProgramService programService, final RegionService regionService, final ThemeService themeService) {
        this.programService = programService;
        this.regionService = regionService;
        this.themeService = themeService;
    }

    @Transactional
    public void uploadCsv(final MultipartFile file) {
        List<EcoTourism> ecoTourisms = new ArrayList<>();
        try (InputStream is = file.getInputStream(); BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            CsvToBean<EcoTourism> ecoTourismCsvToBean = new CsvToBeanBuilder(reader)
                    .withType(EcoTourism.class)
                    .withSkipLines(1)
                    .build();
            ecoTourisms = ecoTourismCsvToBean.parse();
        } catch (IOException e) {
            logger.error(e.toString());
        }

        for (EcoTourism ecotourism : ecoTourisms) {
            List<Region> regions = ecotourism.toRegions();
            List<Theme> themes = ecotourism.toThemes();

            regions = regionService.create(regions);
            themes = themeService.create(themes);

            Program program = ecotourism.toProgram(regions, themes);

            programService.create(program);
        }

    }
}
