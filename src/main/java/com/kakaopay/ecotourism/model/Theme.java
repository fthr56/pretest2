package com.kakaopay.ecotourism.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "theme")
public class Theme {
    @Id
    @GeneratedValue
    @Column(name = "theme_id")
    private Long id;

    @Column(nullable = false)
    private String name;

}
