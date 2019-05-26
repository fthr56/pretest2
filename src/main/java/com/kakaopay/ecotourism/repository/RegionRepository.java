package com.kakaopay.ecotourism.repository;

import com.kakaopay.ecotourism.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, String> {
}
