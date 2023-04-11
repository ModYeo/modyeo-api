package com.co.kr.modyeo.api.bbs.controller;

import com.co.kr.modyeo.api.bbs.domain.dto.request.ReplyCreateRequest;
import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.api.bbs.service.BoardService;
import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureMockMvc
@Transactional
class BoardApiControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;
    @Test
    @DisplayName("get url")
    @WithMockUser
    void getArticles() throws Exception {
        mockMvc.perform(
                get("/api/board/article")
        ).andExpect(
                status().isOk()
        ).andDo(print());
    }

    @Test
    @DisplayName("Article 상세 조회")
    @WithMockUser(username = "1")
    void getArticle() throws Exception {
        mockMvc.perform(
                get("/api/board/article/26")
        ).andExpect(
                status().isOk()
        ).andDo(print());
    }

    @Test
    @DisplayName("reply 삭제")
    @WithMockUser(username = "1")
    void deleteReply() throws Exception {
        mockMvc.perform(
                get("/api/board/article/26")
        ).andExpect(
                status().isOk()
        ).andDo(print());
    }

//    @Test
//    void createReply() throws Exception {
//        String content = objectMapper.writeValueAsString(FIXTURE_REPLY_REQ_01);
//        mockMvc.perform(
//                post("/api/board/reply")
//                        .content(content)
//                        .header("")
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(
//                status().isBadRequest()
//        ).andDo(print());
//    }
}