package com.kakaopay.ecotourism.service;

import com.kakaopay.ecotourism.model.Program;
import com.kakaopay.ecotourism.repository.ProgramRepository;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;

    public ProgramService(final ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public Program create(final Program program) {
        return programRepository.save(program);
    }
}
