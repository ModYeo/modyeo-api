package com.co.kr.modyeo.api.bbs.controller;

import com.co.kr.modyeo.api.bbs.domain.dto.request.ReplyCreateRequest;
import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.api.bbs.service.BoardService;
import com.co.kr.modyeo.api.bbs.service.impl.BoardServiceImpl;
import com.co.kr.modyeo.api.category.controller.CategoryApiController;
import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(
        controllers = BoardApiController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurerAdapter.class)
        }
)
@WithMockUser
@MockBean(JpaMetamodelMappingContext.class)
class BoardApiControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    Category FIXTURE_CAT_01 = Category.of()
            .id(1L)
            .name("테스트 카테고리")
            .imagePath("String")
            .useYn(Yn.Y)
            .build();

    Article FIXTURE_ART_01 = Article.of()
            .id(1L)
            .title("test")
            .content("test")
            .category(FIXTURE_CAT_01)
            .hitCount(1L)
            .replyCount(1)
            .articleRecommendList(new ArrayList<>())
            .build();

    ReplyCreateRequest FIXTURE_REPLY_REQ_01 = ReplyCreateRequest.of()
            .articleId(FIXTURE_ART_01.getId())
            .content("test")
            .replyDepth(1)
            .build();

    @Test
    @DisplayName("get url 테스트")
    void getArticle() throws Exception {
        mockMvc.perform(
                get("/api/board/article")
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