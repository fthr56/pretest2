package com.kakaopay.ecotourism.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReqRecommend {
    private String region;
    private String keyword;
}
