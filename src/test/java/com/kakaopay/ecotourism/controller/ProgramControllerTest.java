package com.kakaopay.ecotourism.controller;

import com.kakaopay.ecotourism.model.EcoTourism;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Program Controller 테스트")
class ProgramControllerTest extends ControllerTestBase {
    private EcoTourism ecoTourism;

    @Test
    @DisplayName("생태 관광 프로그램 검색 성공")
    public void searchEcoTourismTest1() throws Exception {
        this.mockMvc.perform(get("/api/v1/programs/{id}", "reg-2")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("생태 관광 프로그램 검색 실패")
    public void searchEcoTourismTest2() throws Exception {
        this.mockMvc.perform(get("/api/v1/programs/{id}", "reg22-2")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("생태 관광 프로그램 추가")
    @Transactional
    public void createEcoTourism() throws Exception {
        ecoTourism = EcoTourism.builder()
                .programName("오대산국립공원 해피700!! 문화·생태 여행 두번째")
                .programDescription("월정사, 전나무 숲길, 성보박물관 2관")
                .programDetailDescription("★ 월정사 역사·문화 해설\n" + " ★ 전나무 숲 자연해설\n" + " ★ 성보박물관 탐방")
                .regions("강원도")
                .themes("문화생태체험")
                .build();

        MvcResult mvcResult = this.mockMvc.perform(post("/api/v1/programs")
                .content(objectMapper.writeValueAsString(ecoTourism))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString()).contains("prg-111");

    }

    @Test
    @DisplayName("생태 관광 프로그램 수정")
    @Transactional
    public void updateEcoTourism() throws Exception {
        ecoTourism = EcoTourism.builder()
                .programName("오대산국립공원 해피700!! 문화·생태 여행")
                .programDescription("월정사, 전나무 숲길, 성보박물관")
                .programDetailDescription("★ 월정사 역사·문화 해설\n" + " ★ 전나무 숲 자연해설\n" + " ★ 성보박물관 탐방")
                .regions("강원도")
                .themes("생태체험")
                .build();

        this.mockMvc.perform(put("/api/v1/programs/{id}", "prg-1")
                .content(objectMapper.writeValueAsString(ecoTourism))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("지역 컬럼에서 특정 지역에서 진행되는 프로그램명과 테마를 출력")
    public void searchRegionProgramTest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/api/v1/programs/regions")
                .param("region", "강원도 평창군 진부면")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString()).contains("강원도 평창군 진부면").contains("오대산국립공원 힐링캠프");

    }

    @Test
    @DisplayName("프로그램 소개 컬럼에 특정 문자열이 포함된 레코드의 서비스 지역 개수를 세어 출력")
    public void searchDescriptionKeywordTest() throws Exception {
        String result = "{\"keyword\":\"세계문화유산\",\"programs\":[{\"region\":\"경상북도 경주시\",\"count\":2}]}";

        MvcResult mvcResult = this.mockMvc.perform(get("/api/v1/programs/descriptions")
                .param("keyword", "세계문화유산")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(result);
    }

    @Test
    @DisplayName("모든 레코드에 프로그램 상세 정보를 읽어와서 입력 단어의 출현빈도수를 계산하여 출력")
    public void searchDetailDescriptionKeywordTest() throws Exception {
        String result = "{\"keyword\":\"문화\",\"count\":59}";

        MvcResult mvcResult = this.mockMvc.perform(get("/api/v1/programs/detail-descriptions")
                .param("keyword", "문화")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(result);
    }

    @Test
    @DisplayName("지역명과 관광 키 워드를 입력받아 프로그램 코드를 출력하는 API")
    public void recommendEcoTourismTest() throws Exception {
        String result = "{\"program\":\"prg-26\"}";

        MvcResult mvcResult = this.mockMvc.perform(get("/api/v1/programs/recommend")
                .param("region", "경상남도 통영")
                .param("keyword", "생태체")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(result);
    }

}


