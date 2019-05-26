package com.kakaopay.ecotourism.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "program")
public class Program {
    @Id
    @Column(name = "Program_code", nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String detailDescription;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "Program_code")
    private Set<Region> regions;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "Program_code")
    private Set<Theme> themes;

    @Builder
    public Program(final String name, final String description, final String detailDescription, final Set<Region> regions, final Set<Theme> themes) {
        this.name = name;
        this.description = description;
        this.detailDescription = detailDescription;
        this.regions = regions;
        this.themes = themes;
    }
}
