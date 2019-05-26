package com.kakaopay.ecotourism.model.csv;

import com.kakaopay.ecotourism.model.Program;
import com.kakaopay.ecotourism.model.Region;
import com.kakaopay.ecotourism.model.Theme;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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

    public Set<Region> toRegion() {
        String[] regionArr = this.regions.split(",");
        Set<Region> regionSet = new HashSet<>();
        for (String regionName : regionArr) {
            if (regionArr.length != 0) {
                Region region = Region.builder().name(regionName).build();
                regionSet.add(region);
            }
        }
        return regionSet;
    }

    public Set<Theme> toTheme() {
        String[] themeArr = this.themes.split(",");
        Set<Theme> themeSet = new HashSet<>();
        for (String themeName : themeArr) {
            if (themeArr.length != 0) {
                Theme theme = Theme.builder().name(themeName).build();
                themeSet.add(theme);
            }
        }
        return themeSet;
    }

    public Program toProgram(Set<Region> regions, Set<Theme> themes) {
        return Program.builder()
                .name(programName)
                .description(programDescription)
                .detailDescription(programDetailDescription)
                .regions(regions).themes(themes)
                .build();
    }
}
