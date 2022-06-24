package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.member.domain.dto.request.CategoryRequest;
import com.co.kr.modyeo.member.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.member.domain.dto.search.CategorySearch;
import com.co.kr.modyeo.member.domain.entity.Category;
import com.co.kr.modyeo.member.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryRequest categoryRequest = CategoryRequest.of()
            .name("test Category")
            .build();

    private Category category = Category.of()
            .name("test Category")
            .build();


    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void createTest() {
        CategoryRequest categoryRequest = CategoryRequest.of()
                .name("test category")
                .build();

        Category category = categoryRequest.toEntity();
        category = categoryRepository.save(category);

        assertThat(category).isEqualTo(categoryRepository.findByName("test category"));
    }

    @Test
    void createTest2(){
        ArgumentCaptor<Category> captor = ArgumentCaptor.forClass(Category.class);
        given(categoryRepository.save(any()))
                .willReturn(category);

        categoryService.create(categoryRequest);

//        verify(categoryRepository.save(captor.capture()));
        Category saveCategory = captor.getValue();
        assertThat(saveCategory.getName()).isEqualTo(categoryRequest.getName());
    }

    @Test
    void readByOne(){
        List<Category> categories = new ArrayList<>();
        Category category = new Category(1L,"test category");

        categories.add(category);

        given(categoryRepository.searchCategory(new CategorySearch())).willReturn(categories);
        List<Category> categoryList = categoryRepository.searchCategory(new CategorySearch());

        assertThat(categoryList.size()).isEqualTo(1);
    }

    @Test
    void readByOne2(){
        List<Category> categories = new ArrayList<>();
        Category category = new Category(1L,"test category");

        categories.add(category);

        List<CategoryResponse> categoryResponses = categories.stream()
                .map(CategoryResponse::toRes)
                .collect(Collectors.toList());

        given(categoryService.read(new CategorySearch())).willReturn(categoryResponses);
        List<CategoryResponse> categoryResponseList = categoryService.read(new CategorySearch());

        assertThat(categoryResponseList.size()).isEqualTo(1);
    }
}