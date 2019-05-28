package com.kakaopay.ecotourism.controller;

import com.kakaopay.ecotourism.model.EcoTourism;
import com.kakaopay.ecotourism.service.ProgramService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Program Controller 테스트")
class ProgramControllerTest extends ControllerTestBase {
    @MockBean
    private ProgramService programService;
    EcoTourism ecoTourism;

    @Test
    @DisplayName("생태 관광 프로그램 추가")
    public void createEcoTourism() throws Exception {
        ecoTourism  = EcoTourism.builder()
                .programName("오대산국립공원 해피700!! 문화·생태 여행")
                .programDescription("월정사, 전나무 숲길, 성보박물관")
                .programDetailDescription("★ 월정사 역사·문화 해설\n" + " ★ 전나무 숲 자연해설\n" + " ★ 성보박물관 탐방")
                .regions("강원도")
                .themes("생태체험")
                .build();
        //given
        given(programService.createEcoTourism(ecoTourism)).willReturn(ecoTourism.toProgram());
        //when
        //then
        this.mockMvc.perform(post("/api/v1/programs")
                .content(objectMapper.writeValueAsString(ecoTourism))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk());

    }
}
