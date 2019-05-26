package com.kakaopay.ecotourism.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Theme {
    @Id
    @GeneratedValue
    @Column(name = "theme_id")
    private Long id;

    @Column(nullable = false)
    private String name;

}
