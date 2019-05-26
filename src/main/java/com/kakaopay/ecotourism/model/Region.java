package com.kakaopay.ecotourism.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "region")
public class Region {
    @Id
    @Column(name = "region_code", nullable = false, unique = true)
    @GeneratedValue(generator = "region-generator")
    @GenericGenerator(name = "region-generator",
            parameters = @Parameter(name = "prefix", value = "reg"),
            strategy = "com.kakaopay.ecotourism.model.MyGenerator")
    private String code;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "parent_code")
    private Region parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<Region> children;

    @Builder
    public Region(final String name) {
        this.name = name.trim();
    }
}
