package com.kakaopay.ecotourism.response;

import com.kakaopay.ecotourism.model.Program;
import com.kakaopay.ecotourism.model.Region;
import com.kakaopay.ecotourism.model.Theme;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseEcoTourism {
    private String programName;

    private List<Theme> themes;

    private List<Region> regions;

    private String programDescription;

    private String programDetailDescription;

    @Builder
    public ResponseEcoTourism(final Program program) {
        this.programName = program.getName();
        this.themes = program.getThemes();
        this.regions = program.getRegions();
        this.programDescription = program.getDescription();
        this.programDetailDescription = program.getDetailDescription();
    }

}
