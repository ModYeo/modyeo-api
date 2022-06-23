package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.member.domain.dto.request.CategoryRequest;
import com.co.kr.modyeo.member.domain.entity.Category;
import com.co.kr.modyeo.member.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryServiceImplTest {

    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;


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
    @Transactional
    void createTest2(){
        CategoryRequest categoryRequest = CategoryRequest.of()
                .name("test category")
                .build();
        categoryService.create(categoryRequest);
        Mockito.verify(categoryRepository).save(ArgumentMatchers.any());
    }
}