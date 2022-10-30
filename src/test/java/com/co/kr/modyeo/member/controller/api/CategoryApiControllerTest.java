package com.co.kr.modyeo.member.controller.api;

import com.co.kr.modyeo.api.category.controller.CategoryApiController;
import com.co.kr.modyeo.api.category.domain.dto.request.CategoryCreateRequest;
import com.co.kr.modyeo.api.category.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.api.category.domain.dto.search.CategorySearch;
import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.category.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(
        controllers = CategoryApiController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurerAdapter.class)
        }
)
@WithMockUser
class CategoryApiControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    @DisplayName("카테고리 생성 성공")
    void createTest() throws Exception {
        CategoryCreateRequest categoryCreateRequest = CategoryCreateRequest.of()
                .name("test category")
                .build();

        String request = objectMapper.writeValueAsString(categoryCreateRequest);

//        given(categoryService.createCategory(any()))
//                .willReturn(Category.of()
//                        .id(1L)
//                        .name("test category")
//                        .build());

        mockMvc.perform(
                post("/api/category")
                        .with(csrf())
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isCreated()
        ).andDo(print());
    }

    @ParameterizedTest
    @ValueSource(ints = {10,20,30})
    void readTest(int num) throws Exception {
        CategorySearch categorySearch = CategorySearch
                .builder()
                .build();

        List<Category> categoryList = new ArrayList<>();
        for (Long i = 0L; i < num;i++){
            Category category = Category.of()
                    .id(i)
                    .name("test Category" + i)
                    .build();

            categoryList.add(category);
        }

        List<CategoryResponse> responses =
                categoryList.stream().map(CategoryResponse::toRes)
                        .collect(Collectors.toList());

        given(categoryService.getCategories(any()))
                .willReturn(responses);

        mockMvc.perform(
              get("/api/category")
                      .content(objectMapper.writeValueAsBytes(categorySearch))
                      .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()",equalTo(num)))
                .andDo(print());
    }

//    @Test
//    void updateTest() throws Exception {
//        CategoryCreateRequest categoryCreateRequest = CategoryCreateRequest.of()
//                .name("update category")
//                .build();
//
//        given(categoryService.updateCategory(any()))
//                .willReturn(Category.of()
//                        .id(1L)
//                        .name("update category")
//                        .build());
//
//        mockMvc.perform(
//                patch("/api/category")
//                        .with(csrf())
//                        .content(objectMapper.writeValueAsBytes(categoryCreateRequest))
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(status().isOk())
//                .andDo(print());
//    }
}