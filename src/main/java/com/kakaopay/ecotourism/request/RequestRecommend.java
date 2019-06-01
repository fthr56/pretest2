package com.kakaopay.ecotourism.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestRecommend {
    private String region;
    private String keyword;
}
