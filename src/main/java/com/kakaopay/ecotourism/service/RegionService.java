package com.kakaopay.ecotourism.service;

import com.kakaopay.ecotourism.model.Region;
import com.kakaopay.ecotourism.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RegionService {
    private final RegionRepository regionRepository;

    public RegionService(final RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    // TODO Region 저장 후 pk값 추가 된 Set 반환
    public Set<Region> create(final Set<Region> regions) {
        return null;
    }
}
