package com.kakaopay.ecotourism.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResRecommend {
    private String program;

    public ResRecommend(final String program) {
        this.program = program;
    }
}
