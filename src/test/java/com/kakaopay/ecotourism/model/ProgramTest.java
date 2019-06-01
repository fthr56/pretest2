package com.kakaopay.ecotourism.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Program 테스트")
class ProgramTest {
    private static final String DESCRIPTION = "월정사, 전나무 숲길, 성보박물관";

    private static Program program;
    private String keyword;

    @BeforeAll
    public static void setUp() {
        program = Program.builder()
                .description(DESCRIPTION)
                .build();
    }

    @Test
    @DisplayName("프로그램 소개에 특정 키워드 포함된 경우 테스트")
    public void descriptionContainKeywordTest1() {
        keyword = "월정사";
        assertThat(program.descriptionContain(keyword)).isTrue();
    }

    @Test
    @DisplayName("프로그램 소개에 특정 키워드 포함 안된 경우 테스트")
    public void descriptionContainKeywordTest2() {
        keyword = "바다";
        assertThat(program.descriptionContain(keyword)).isFalse();
    }
}
