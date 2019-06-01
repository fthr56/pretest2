package com.kakaopay.ecotourism.response;

import com.kakaopay.ecotourism.model.Program;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResRegionProgram {
    private String region;

    private List<RegionProgram> programs;

    @Builder
    public ResRegionProgram(final String region, final List<RegionProgram> programs) {
        this.region = region;
        this.programs = programs;
    }

    public static ResRegionProgram programsToRegionProgram(String regionName, List<Program> programs) {
        List<RegionProgram> regionPrograms = new ArrayList<>();

        for (Program program : programs) {
            RegionProgram regionProgram = new RegionProgram(program.getName(), program.getFullTheme());
            regionPrograms.add(regionProgram);
        }

        ResRegionProgram resRegionProgram = ResRegionProgram.builder()
                .region(regionName)
                .programs(regionPrograms)
                .build();

        return resRegionProgram;

    }
}
