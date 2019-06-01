package com.kakaopay.ecotourism.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDetailDescriptionKeyword {
    private String keyword;
    private int count;
}