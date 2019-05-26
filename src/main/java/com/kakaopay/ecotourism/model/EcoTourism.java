package com.kakaopay.ecotourism.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EcoTourism {
    @CsvBindByPosition(position = 1)
    String programName;

    @CsvBindByPosition(position = 2)
    String themes;

    @CsvBindByPosition(position = 3)
    String regions;

    @CsvBindByPosition(position = 4)
    String programDescription;

    @CsvBindByPosition(position = 5)
    String programDetailDescription;

    public List<Region> toRegions() {
        String[] regionArr = this.regions.split(",");
        List<Region> regionList = new ArrayList<>();
        for (String regionName : regionArr) {
            if (regionArr.length != 0) {
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
            if (themeArr.length != 0) {
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
}
