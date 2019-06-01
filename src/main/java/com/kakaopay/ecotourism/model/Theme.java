package com.kakaopay.ecotourism.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theme_id")
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private String name;

    @Builder
    public Theme(final String name) {
        this.name = name.trim();
    }

    public boolean containKeyword(final String keyword) {
        return name.contains(keyword);
    }
}
