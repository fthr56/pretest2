package com.kakaopay.ecotourism.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseRecommend {
    private String program;

    public ResponseRecommend(final String program) {
        this.program = program;
    }
}
