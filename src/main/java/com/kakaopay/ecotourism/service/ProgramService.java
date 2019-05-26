package com.kakaopay.ecotourism.service;

import com.kakaopay.ecotourism.model.Program;
import com.kakaopay.ecotourism.repository.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;

    public ProgramService(final ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Transactional
    public Program create(final Program program) {
        return programRepository.save(program);
    }
}
