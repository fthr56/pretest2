package com.kakaopay.ecotourism.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "program")
public class Program {
    @Id
    @Column(name = "program_code", nullable = false, unique = true)
    @GeneratedValue(generator = "program-generator")
    @GenericGenerator(name = "program-generator",
            parameters = @Parameter(name = "prefix", value = "prg"),
            strategy = "com.kakaopay.ecotourism.model.MyGenerator")
    private String code;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(length = 500)
    private String detailDescription;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "program_region",
            joinColumns = @JoinColumn(name = "program_code"),
            inverseJoinColumns = @JoinColumn(name = "region_code"))
    private List<Region> regions;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "program_theme",
            joinColumns = @JoinColumn(name = "program_code"),
            inverseJoinColumns = @JoinColumn(name = "theme_id"))
    private List<Theme> themes;

    @Builder
    public Program(final String name, final String description, final String detailDescription, final List<Region> regions, final List<Theme> themes) {
        this.name = name;
        this.description = description;
        this.detailDescription = detailDescription;
        this.regions = regions;
        this.themes = themes;
    }

    public void update(final EcoTourism ecoTourism) {
        if (!StringUtils.isEmpty(ecoTourism.getProgramName())) {
            this.name = ecoTourism.getProgramName();
        }
        if (!StringUtils.isEmpty(ecoTourism.getProgramDescription())) {
            this.description = ecoTourism.getProgramDescription();
        }
        if (!StringUtils.isEmpty(ecoTourism.getProgramDetailDescription())) {
            this.detailDescription = ecoTourism.getProgramDetailDescription();
        }

    }
}
