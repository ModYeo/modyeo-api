package com.co.kr.modyeo.api.inquiry.controller;

import com.co.kr.modyeo.api.inquiry.service.InquiryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(
        controllers = InquiryApiController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurerAdapter.class)
        }
)
@WithMockUser
@MockBean(JpaMetamodelMappingContext.class)
public class InquiryApiControllerTest {

        @Autowired
        ObjectMapper objectMapper;

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private InquiryService inquiryService;

        @Test
        @DisplayName("get url 테스트")
        void getArticle() throws Exception {
                mockMvc.perform(
                        get("/api/inquiry")
                ).andExpect(
                        status().isOk()
                ).andDo(print());
        }
}
