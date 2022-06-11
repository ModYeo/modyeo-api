package com.co.kr.modyeo.member.auth.controller;

import com.co.kr.modyeo.member.auth.service.AuthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(value = AuthController.class)
@AutoConfigureMockMvc
@DisplayName(value = "Auth Controller test")
class AuthControllerTest {
    final String rootUrl = "/auth";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthService authService;

    @Test
    void join_success(){
    }
}