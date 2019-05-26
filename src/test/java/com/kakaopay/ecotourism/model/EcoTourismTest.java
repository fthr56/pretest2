package com.kakaopay.ecotourism.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("EcoTourism class Test")
class EcoTourismTest {
    private EcoTourism ecoTourism;
    private Region region;
    private Theme theme;
    String regionString;
    String themeString;

    @DisplayName("Region이 하나인 경우")
    @Test
    public void toRegionsTest1() {
        regionString = "강원도 오대산국립공원";
        themeString = "문화생태체험";

        ecoTourism = getEcoTourism(regionString, themeString);

        region = Region.builder()
                .name("강원도 오대산국립공원")
                .build();

        List<Region> regions = ecoTourism.toRegions();
        List<Region> expectedRegions = Arrays.asList(region);

        assertThat(regions).isEqualTo(expectedRegions);

    }

    @DisplayName("Region이 두개인 경우")
    @Test
    public void toRegionsTest2() {
        regionString = "강원도 오대산국립공원, 충청도";
        themeString = "문화생태체험";

        ecoTourism = getEcoTourism(regionString, themeString);

        region = Region.builder()
                .name("강원도 오대산국립공원")
                .build();

        Region region2 = Region.builder()
                .name("충청도")
                .build();


        List<Region> regions = ecoTourism.toRegions();
        List<Region> expectedRegions = Arrays.asList(region, region2);

        assertThat(regions).isEqualTo(expectedRegions);

    }

    @DisplayName("Region이 하나이면서 마지막 빈 콤마")
    @Test
    public void toRegionsTest3() {
        regionString = "강원도 오대산국립공원,";
        themeString = "문화생태체험";

        ecoTourism = getEcoTourism(regionString, themeString);

        region = Region.builder()
                .name("강원도 오대산국립공원")
                .build();


        List<Region> regions = ecoTourism.toRegions();
        List<Region> expectedRegions = Arrays.asList(region);

        assertThat(regions).isEqualTo(expectedRegions);

    }

    @DisplayName("Region이 하나이면서 맨 처음 빈 콤마")
    @Test
    public void toRegionsTest4() {
        regionString = ",강원도 오대산국립공원";
        themeString = "문화생태체험";

        ecoTourism = getEcoTourism(regionString, themeString);

        region = Region.builder()
                .name("강원도 오대산국립공원")
                .build();


        List<Region> regions = ecoTourism.toRegions();
        List<Region> expectedRegions = Arrays.asList(region);

        assertThat(regions).isEqualTo(expectedRegions);

    }


    @DisplayName("Region이 하나이면서 맨 처음, 맨 뒤 빈 콤마")
    @Test
    public void toRegionsTest5() {
        regionString = ",강원도 오대산국립공원,";
        themeString = "문화생태체험";

        ecoTourism = getEcoTourism(regionString, themeString);

        region = Region.builder()
                .name("강원도 오대산국립공원")
                .build();


        List<Region> regions = ecoTourism.toRegions();
        List<Region> expectedRegions = Arrays.asList(region);

        assertThat(regions).isEqualTo(expectedRegions);
    }

    @DisplayName("Theme 하나이면서 맨 처음, 맨 뒤 빈 콤마")
    @Test
    public void toThemesTest() {
        regionString = ",강원도 오대산국립공원";
        themeString = ",문화생태체험,자연생태체험,";

        ecoTourism = getEcoTourism(regionString, themeString);

        theme = Theme.builder()
                .name("문화생태체험")
                .build();

        Theme theme2 = Theme.builder()
                .name("자연생태체험")
                .build();


        List<Theme> themes = ecoTourism.toThemes();
        List<Theme> expectedThemes = Arrays.asList(theme, theme2);

        assertThat(themes).isEqualTo(expectedThemes);
    }

    @DisplayName("")
    @Test
    public void toProgramTest() {
        regionString = ",강원도 오대산국립공원";
        themeString = "문화생태체험,자연생태체험";

        ecoTourism = getEcoTourism(regionString, themeString);

        List<Region> regions = ecoTourism.toRegions();
        List<Theme> themes = ecoTourism.toThemes();
        Program program = ecoTourism.toProgram(regions, themes);

        Program expectedProgram = Program.builder()
                .name("오대산국립공원 해피700!! 문화·생태 여행")
                .description("월정사, 전나무 숲길, 성보박물관")
                .detailDescription("★ 월정사 역사·문화 해설\n" + " ★ 전나무 숲 자연해설\n" + " ★ 성보박물관 탐방")
                .regions(regions)
                .themes(themes)
                .build();

        assertThat(program).isEqualTo(expectedProgram);
    }


    private EcoTourism getEcoTourism(String regions, String themes) {
        return EcoTourism.builder()
                .programName("오대산국립공원 해피700!! 문화·생태 여행")
                .programDescription("월정사, 전나무 숲길, 성보박물관")
                .programDetailDescription("★ 월정사 역사·문화 해설\n" + " ★ 전나무 숲 자연해설\n" + " ★ 성보박물관 탐방")
                .regions(regions)
                .themes(themes)
                .build();
    }
}
