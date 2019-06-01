package com.kakaopay.ecotourism.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DescriptionProgram {
    private String region;
    private int count;

    public DescriptionProgram(final String regionName, final Integer count) {
        this.region = regionName;
        this.count = count;
    }
}
