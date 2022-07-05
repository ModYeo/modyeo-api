package com.co.kr.modyeo.member.controller.api;

import com.co.kr.modyeo.api.category.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import com.co.kr.modyeo.api.team.domain.dto.search.TeamSearch;
import com.co.kr.modyeo.api.team.service.TeamService;
import com.co.kr.modyeo.api.team.controller.TeamApiController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(
        controllers = TeamApiController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurerAdapter.class)
        }
)
@WithMockUser
class TeamApiControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    @Test
    void getCrew() throws Exception {
        TeamSearch teamSearch = TeamSearch.builder()
                .categoryId(1L)
                .build();

        PageRequest pageRequest = PageRequest.of(0,20);
        List<CategoryResponse> categories = new ArrayList<>();
        List<TeamResponse> crews = new ArrayList<>();
        for (Long i = 0L; i < 10; i++){
            TeamResponse teamResponse = new TeamResponse();
            teamResponse.setId(i);
            teamResponse.setName("test crew" + i);

            for (Long j = 0L; j < 2;j++){
                CategoryResponse categoryResponse = CategoryResponse.of()
                        .id(j)
                        .name("test category" + j)
                        .build();

                categories.add(categoryResponse);
            }

            crews.add(teamResponse);
        }
        Slice<TeamResponse> crewResponses = new SliceImpl<>(crews,pageRequest,true);
        given(teamService.getTeam(any())).willReturn(crewResponses);

        mockMvc.perform(
                get("/api/crew") .with(csrf())
                        .content(objectMapper.writeValueAsString(teamSearch))
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