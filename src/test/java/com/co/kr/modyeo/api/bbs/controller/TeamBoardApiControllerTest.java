package com.co.kr.modyeo.api.bbs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureMockMvc
@Transactional
class TeamBoardApiControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("article 조회")
    @WithMockUser(username = "1")
    void getArticle() throws Exception {
        mockMvc.perform(
                get("/api/team-board/article/7")
        ).andExpect(
                status().isOk()
        ).andDo(print());
    }

    @Test
    @DisplayName("reply 삭제")
    @WithMockUser(username = "19")
    void deleteReply() throws Exception {
        mockMvc.perform(
                delete("/api/team-board/reply/9")
        ).andExpect(
                status().is2xxSuccessful()
        ).andDo(print());
    }

    @Test
    @DisplayName("댓글 조회")
    @WithMockUser(username = "1")
    void getReply() throws Exception {
        mockMvc.perform(
                get("/api/team-board/reply/9")
        ).andExpect(
                status().isOk()
        ).andDo(print());
    }
}