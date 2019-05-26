package com.kakaopay.ecotourism.service;

import com.kakaopay.ecotourism.model.Theme;
import com.kakaopay.ecotourism.repository.ThemeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {
    private final ThemeRepository themeRepository;

    public ThemeService(final ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Transactional
    public List<Theme> create(final List<Theme> themes) {
        List<Theme> themeList = new ArrayList<>();

        for (Theme theme : themes) {
            Optional<Theme> dbTheme = themeRepository.findByName(theme.getName());
            if (dbTheme.isPresent()) {
                themeList.add(dbTheme.get());
            } else {
                themeList.add(themeRepository.save(theme));
            }
        }
        return themeList;
    }
}
