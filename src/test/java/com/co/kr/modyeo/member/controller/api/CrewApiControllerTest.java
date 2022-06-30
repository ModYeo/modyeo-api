package com.co.kr.modyeo.member.controller.api;

import com.co.kr.modyeo.member.domain.dto.request.CrewRequest;
import com.co.kr.modyeo.member.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.member.domain.dto.response.CrewResponse;
import com.co.kr.modyeo.member.domain.dto.search.CrewSearch;
import com.co.kr.modyeo.member.domain.entity.Crew;
import com.co.kr.modyeo.member.domain.entity.link.CrewCategory;
import com.co.kr.modyeo.member.service.CrewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(
        controllers = CrewApiController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurerAdapter.class)
        }
)
@WithMockUser
class CrewApiControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CrewService crewService;

    @Test
    void getCrew() throws Exception {
        CrewSearch crewSearch = CrewSearch.builder()
                .categoryId(1L)
                .build();

        PageRequest pageRequest = PageRequest.of(0,20);
        List<CategoryResponse> categories = new ArrayList<>();
        List<CrewResponse> crews = new ArrayList<>();
        for (Long i = 0L; i < 10; i++){
            CrewResponse crewResponse = new CrewResponse();
            crewResponse.setId(i);
            crewResponse.setName("test crew" + i);

            for (Long j = 0L; j < 2;j++){
                CategoryResponse categoryResponse = CategoryResponse.of()
                        .id(j)
                        .name("test category" + j)
                        .build();

                categories.add(categoryResponse);
            }

            crews.add(crewResponse);
        }
        Slice<CrewResponse> crewResponses = new SliceImpl<>(crews,pageRequest,true);
        given(crewService.getCrew(any())).willReturn(crewResponses);

        mockMvc.perform(
                get("/api/crew") .with(csrf())
                        .content(objectMapper.writeValueAsString(crewSearch))
        ).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void updateCrew() {

    }

    @Test
    void createCrew() {
    }
}