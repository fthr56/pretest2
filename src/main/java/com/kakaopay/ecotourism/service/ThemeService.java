package com.kakaopay.ecotourism.service;

import com.kakaopay.ecotourism.model.Theme;
import com.kakaopay.ecotourism.repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ThemeService {
    private final ThemeRepository themeRepository;

    public ThemeService(final ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    // TODO theme 저장 후 pk값 추가 된 Set 반환
    public Set<Theme> create(final Set<Theme> themes) {
        return null;
    }
}
