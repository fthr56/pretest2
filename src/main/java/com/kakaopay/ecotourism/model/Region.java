package com.kakaopay.ecotourism.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "region")
public class Region {
    @Id
    @Column(name = "region_code", nullable = false, unique = true)
    @GeneratedValue(generator = "region-generator")
    @GenericGenerator(name = "region-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "reg"),
            strategy = "com.kakaopay.ecotourism.model.MyGenerator")
    private String code;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_code")
    private Region parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<Region> children;

    @Builder
    public Region(final String name) {
        this.name = name;
    }
}
