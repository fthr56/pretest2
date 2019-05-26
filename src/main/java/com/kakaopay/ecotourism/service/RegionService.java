package com.kakaopay.ecotourism.service;

import com.kakaopay.ecotourism.model.Region;
import com.kakaopay.ecotourism.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {
    private final RegionRepository regionRepository;

    public RegionService(final RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> create(final List<Region> regions) {
        return regionRepository.saveAll(regions);
    }
}
