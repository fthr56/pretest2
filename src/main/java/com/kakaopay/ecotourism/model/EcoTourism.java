package com.kakaopay.ecotourism.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class EcoTourism {
    @CsvBindByPosition(position = 1)
    private String programName;

    @CsvBindByPosition(position = 2)
    private String themes;

    @CsvBindByPosition(position = 3)
    private String regions;

    @CsvBindByPosition(position = 4)
    private String programDescription;

    @CsvBindByPosition(position = 5)
    private String programDetailDescription;

    @Builder
    public EcoTourism(final String programName, final String themes, final String regions, final String programDescription, final String programDetailDescription) {
        this.programName = programName;
        this.themes = themes;
        this.regions = regions;
        this.programDescription = programDescription;
        this.programDetailDescription = programDetailDescription;
    }

    public List<Region> toRegions() {
        String[] regionArr = this.regions.split(",");
        List<Region> regionList = new ArrayList<>();
        for (String regionName : regionArr) {
            if (!StringUtils.isEmpty(regionName)) {
                Region region = Region.builder().name(regionName).build();
                regionList.add(region);
            }
        }
        return regionList;
    }

    public List<Theme> toThemes() {
        String[] themeArr = this.themes.split(",");
        List<Theme> themeList = new ArrayList<>();
        for (String themeName : themeArr) {
            if (!StringUtils.isEmpty(themeName)) {
                Theme theme = Theme.builder().name(themeName).build();
                themeList.add(theme);
            }
        }
        return themeList;
    }

    public Program toProgram(List<Region> regions, List<Theme> themes) {
        return Program.builder()
                .name(programName)
                .description(programDescription)
                .detailDescription(programDetailDescription)
                .regions(regions).themes(themes)
                .build();
    }

    public Program toProgram() {
        return Program.builder()
                .name(programName)
                .description(programDescription)
                .detailDescription(programDetailDescription)
                .regions(toRegions()).themes(toThemes())
                .build();
    }
}
