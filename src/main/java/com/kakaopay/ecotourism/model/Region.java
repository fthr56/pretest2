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
@Table(name = "region")
public class Region {
    @Id
    @Column(name = "region_code", nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_code")
    private Region parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<Region> children;

}
