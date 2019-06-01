package com.kakaopay.ecotourism.model;

import com.kakaopay.ecotourism.response.ResponseRecommend;
import lombok.Builder;
import org.springframework.util.StringUtils;

public class Recommend {
    private final static int THEME_WEIGHT = 30;
    private final static int DESCRIPTION_WEIGHT = 20;
    private final static int DETAIL_DESCRIPTION_WEIGHT = 10;

    private String program;
    private String theme;
    private String description;
    private String detailDescription;

    @Builder
    public Recommend(final String program, final String theme, final String description, final String detailDescription) {
        this.program = program;
        this.theme = theme;
        this.description = description;
        this.detailDescription = detailDescription;
    }

    public int calculatePoint(String keyword) {
        int themePoint = StringUtils.countOccurrencesOf(theme, keyword);
        int descriptionPoint = StringUtils.countOccurrencesOf(description, keyword);
        int detailDescriptionPoint = StringUtils.countOccurrencesOf(detailDescription, keyword);

        return calculateWeight(themePoint, descriptionPoint, detailDescriptionPoint);
    }

    private static int calculateWeight(int themePoint, int descriptionPoint, int detailDescriptionPoint) {
        return (themePoint * THEME_WEIGHT) + (descriptionPoint * DESCRIPTION_WEIGHT) + (detailDescriptionPoint * DETAIL_DESCRIPTION_WEIGHT);
    }

    public int compareRecommendPoint(String keyword, Recommend other) {
        int point = this.calculatePoint(keyword);
        int otherPoint = other.calculatePoint(keyword);
        return Integer.compare(point, otherPoint);
    }

    public ResponseRecommend toResponseRecommend() {
        return new ResponseRecommend(program);
    }
}
