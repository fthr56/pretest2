package com.kakaopay.ecotourism.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "id")
@Table(name = "theme")
public class Theme {
    @Id
    @GeneratedValue
    @Column(name = "theme_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Builder
    public Theme(final String name) {
        this.name = name.trim();
    }
}
