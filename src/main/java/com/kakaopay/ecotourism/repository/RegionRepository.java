package com.kakaopay.ecotourism.repository;

import com.kakaopay.ecotourism.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, String> {
    Optional<Region> findByName(String name);
}
