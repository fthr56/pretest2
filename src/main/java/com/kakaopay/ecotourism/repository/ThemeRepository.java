package com.kakaopay.ecotourism.repository;

import com.kakaopay.ecotourism.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
}
