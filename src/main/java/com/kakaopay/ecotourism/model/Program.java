package com.kakaopay.ecotourism.model;

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
    private Set<Theme> theme;

}
