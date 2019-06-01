package com.kakaopay.ecotourism.controller;

import com.kakaopay.ecotourism.model.EcoTourism;
import com.kakaopay.ecotourism.service.ProgramService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Program Controller 테스트")
class ProgramControllerTest extends ControllerTestBase {
    @MockBean
    private ProgramService programService;
    private EcoTourism ecoTourism;

    @Test
    @DisplayName("생태 관광 프로그램 검색")
    public void searchEcoTourism() throws Exception {
        this.mockMvc.perform(get("/api/v1/programs/{id}", "reg-2")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("생태 관광 프로그램 추가")
    public void createEcoTourism() throws Exception {
        ecoTourism = EcoTourism.builder()
                .programName("오대산국립공원 해피700!! 문화·생태 여행")
                .programDescription("월정사, 전나무 숲길, 성보박물관")
                .programDetailDescription("★ 월정사 역사·문화 해설\n" + " ★ 전나무 숲 자연해설\n" + " ★ 성보박물관 탐방")
                .regions("강원도")
                .themes("생태체험")
                .build();

        given(programService.createEcoTourism(ecoTourism)).willReturn(ecoTourism.toProgram());

        this.mockMvc.perform(post("/api/v1/programs")
                .content(objectMapper.writeValueAsString(ecoTourism))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("생태 관광 프로그램 수정")
    public void updateEcoTourism() throws Exception {
        ecoTourism = EcoTourism.builder()
                .programName("오대산국립공원 해피700!! 문화·생태 여행")
                .programDescription("월정사, 전나무 숲길, 성보박물관")
                .programDetailDescription("★ 월정사 역사·문화 해설\n" + " ★ 전나무 숲 자연해설\n" + " ★ 성보박물관 탐방")
                .regions("강원도")
                .themes("생태체험")
                .build();

        given(programService.createEcoTourism(ecoTourism)).willReturn(ecoTourism.toProgram());

        this.mockMvc.perform(put("/api/v1/programs/{id}", "prg-1")
                .content(objectMapper.writeValueAsString(ecoTourism))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("지역 컬럼에서 특정 지역에서 진행되는 프로그램명과 테마를 출력")
    public void searchRegionProgramTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/programs/regions")
                .param("region", "평창군")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }
}
