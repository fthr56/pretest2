package com.kakaopay.ecotourism.repository;

import com.kakaopay.ecotourism.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, String> {
}
