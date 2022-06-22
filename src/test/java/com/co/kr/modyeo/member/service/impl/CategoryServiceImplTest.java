package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.member.domain.dto.request.CategoryRequest;
import com.co.kr.modyeo.member.domain.entity.Category;
import com.co.kr.modyeo.member.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CategoryServiceImplTest {

    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void createTest() {
        CategoryRequest categoryRequest = CategoryRequest.of()
                .name("test category")
                .build();

        Category category = categoryRequest.toEntity();
        category = categoryRepository.save(category);

        assertThat(category.getId()).isEqualTo(1);
        assertThat(category.getName()).isEqualTo("test category");
    }
}