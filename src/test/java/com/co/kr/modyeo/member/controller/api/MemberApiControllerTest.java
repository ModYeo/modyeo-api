package com.co.kr.modyeo.member.controller.api;

import com.co.kr.modyeo.api.member.controller.MemberApiController;
import com.co.kr.modyeo.api.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;


@WebMvcTest
@EnableWebMvc
@AutoConfigureMockMvc
@ContextConfiguration(classes = {MemberApiController.class})
class MemberApiControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

//    @Test
//    @WithMockUser
//    void getMembers() throws Exception {
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/api/member/all").with(csrf().useInvalidToken())
//        ).andExpect(
//                MockMvcResultMatchers.status().isOk()
//        ).andDo(MockMvcResultHandlers.print());
//    }

}