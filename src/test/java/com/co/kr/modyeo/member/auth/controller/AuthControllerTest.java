package com.co.kr.modyeo.member.auth.controller;

import com.co.kr.modyeo.api.bbs.domain.dto.response.ReplyResponse;
import com.co.kr.modyeo.api.member.auth.controller.AuthController;
import com.co.kr.modyeo.api.member.auth.domain.dto.MemberJoinDto;
import com.co.kr.modyeo.api.member.auth.service.AuthService;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Disabled
@ActiveProfiles("dev")
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void checkEmail() throws Exception {
        mockMvc.perform(
                get("/api/auth/overlap-mail")
                        .param("email","qws458@naver.com")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("disable"));
    }

    @Test
    void checkEmailEnable() throws Exception {
        mockMvc.perform(
                        get("/api/auth/overlap-mail")
                                .param("email","test1234124@naver.com")
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("enable"));
    }

//    @Test
//    void join_success() throws Exception {
//        MemberJoinDto memberJoinDto = new MemberJoinDto("qws458","Qwpo1209");
//
//        mockMvc.perform(
//                post("/auth/signup")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(memberJoinDto))
//        ).andDo(print())
//                .andExpect(status().isCreated());
//    }
}