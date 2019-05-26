package com.kakaopay.ecotourism.service;

import com.kakaopay.ecotourism.model.Theme;
import com.kakaopay.ecotourism.repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {
    private final ThemeRepository themeRepository;

    public ThemeService(final ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public List<Theme> create(final List<Theme> themes) {
        return themeRepository.saveAll(themes);
    }
}
