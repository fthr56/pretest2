package com.kakaopay.ecotourism.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "program")
public class Program {
    @Id
    @Column(name = "Program_code", nullable = false, unique = true)
    @GeneratedValue(generator = "program-generator")
    @GenericGenerator(name = "program-generator",
            parameters = @Parameter(name = "prefix", value = "prg"),
            strategy = "com.kakaopay.ecotourism.model.MyGenerator")
    private String code;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String detailDescription;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "Program_code")
    private List<Region> regions;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "Program_code")
    private List<Theme> themes;

    @Builder
    public Program(final String name, final String description, final String detailDescription, final List<Region> regions, final List<Theme> themes) {
        this.name = name;
        this.description = description;
        this.detailDescription = detailDescription;
        this.regions = regions;
        this.themes = themes;
    }
}
