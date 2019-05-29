package com.kakaopay.ecotourism.service;

import com.kakaopay.ecotourism.model.Region;
import com.kakaopay.ecotourism.repository.RegionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    private final RegionRepository regionRepository;

    public RegionService(final RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Transactional
    public List<Region> create(final List<Region> regions) {
        List<Region> regionList = new ArrayList<>();

        for (Region region : regions) {
            Optional<Region> dbRegion = regionRepository.findByName(region.getName());
            if (dbRegion.isPresent()) {
                regionList.add(dbRegion.get());
            } else {
                regionList.add(regionRepository.save(region));
            }
        }

        return regionList;
    }

    public Region findRegion(final String id) {
        return regionRepository.findById(id).get();
    }
}
