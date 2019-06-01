package com.kakaopay.ecotourism.response;

import com.kakaopay.ecotourism.model.Program;
import com.kakaopay.ecotourism.model.Region;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDescriptionProgram {
    private String keyword;
    private List<DescriptionProgram> programs;

    @Builder
    public ResponseDescriptionProgram(final String keyword, final List<DescriptionProgram> programs) {
        this.keyword = keyword;
        this.programs = programs;
    }

    public static ResponseDescriptionProgram programsToResponseDescriptionProgram(String keyword, List<Program> programs) {
        Map<String, Integer> regionMap = getStringIntegerMap(programs);
        List<DescriptionProgram> tempPrograms = getPrograms(regionMap);

        return ResponseDescriptionProgram.builder()
                .keyword(keyword)
                .programs(tempPrograms)
                .build();
    }

    private static Map<String, Integer> getStringIntegerMap(final List<Program> programs) {
        Map<String, Integer> regionMap = new HashMap<>();

        for (Program program : programs) {
            for (Region region : program.getRegions()) {
                regionMap.put(region.getName(), regionMap.getOrDefault(region.getName(), 0) + 1);
            }
        }
        return regionMap;
    }

    private static List<DescriptionProgram> getPrograms(final Map<String, Integer> regionMap) {
        List<DescriptionProgram> tempPrograms = new ArrayList<>();
        for (String regionName : regionMap.keySet()) {
            DescriptionProgram descriptionProgram = new DescriptionProgram(regionName, regionMap.get(regionName));
            tempPrograms.add(descriptionProgram);
        }
        return tempPrograms;
    }

}
