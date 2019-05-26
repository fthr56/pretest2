package com.kakaopay.ecotourism.repository;

import com.kakaopay.ecotourism.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    Optional<Theme> findByName(String name);
}
