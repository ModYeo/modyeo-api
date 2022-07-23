package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.api.category.domain.dto.request.CategoryUpdateRequest;
import com.co.kr.modyeo.api.category.service.impl.CategoryServiceImpl;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.CategoryErrorCode;
import com.co.kr.modyeo.api.category.domain.dto.request.CategoryCreateRequest;
import com.co.kr.modyeo.api.category.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.api.category.domain.dto.search.CategorySearch;
import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.category.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryCreateRequest categoryCreateRequest = CategoryCreateRequest.of()
            .name("test Category")
            .build();

    private CategoryUpdateRequest categoryUpdateRequest = CategoryUpdateRequest.of()
            .categoryId(1L)
            .name("update test")
            .build();

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void createTest() {
        CategoryCreateRequest categoryCreateRequest = CategoryCreateRequest.of()
                .name("test category")
                .build();

        Category category = CategoryCreateRequest.createCategory(categoryCreateRequest);
        category = categoryRepository.save(category);

        assertThat(category).isEqualTo(categoryRepository.findByName("test category"));
    }

    @Test
    void createTest2(){
        Category category = Category.of()
                .id(1L)
                .name("test Category")
                .build();

        given(categoryRepository.save(any()))
                .willReturn(category);

        category = categoryService.createCategory(categoryCreateRequest);
        assertThat(category.getId()).isEqualTo(1L);
    }

    @Test
    void readByOne(){
        List<Category> categories = new ArrayList<>();
        Category category = new Category(1L,"test category", Yn.Y);

        categories.add(category);

        given(categoryRepository.searchCategory(new CategorySearch())).willReturn(categories);
        List<Category> categoryList = categoryRepository.searchCategory(new CategorySearch());

        assertThat(categoryList.size()).isEqualTo(1);
    }

    @Test
    void readByOne2(){
        List<Category> categories = new ArrayList<>();
        Category category = new Category(1L,"test category",Yn.Y);

        categories.add(category);

        List<CategoryResponse> categoryResponses = categories.stream()
                .map(CategoryResponse::toRes)
                .collect(Collectors.toList());

        given(categoryService.getCategories(new CategorySearch())).willReturn(categoryResponses);
        List<CategoryResponse> categoryResponseList = categoryService.getCategories(new CategorySearch());

        assertThat(categoryResponseList.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("카테고리 변경 테스트 성공")
    void updateCategory(){
        Category category = Category.of()
                .id(1004L)
                .name("category Test")
                .build();

        given(categoryRepository.findById(any())).willReturn(Optional.of(category));
        Optional<Category> findCategory = categoryRepository.findById(1004L);

        category = findCategory.get();
        category.changeCategory("update test",Yn.Y);
        given(categoryRepository.save(any())).willReturn(category);
        category = categoryRepository.save(category);

        assertThat(category.getName()).isEqualTo("update test");
    }

    @Test
    @DisplayName("카테고리 변경 테스트2")
    void updateCategoryTest2(){
        Category category = Category.of()
                .id(1004L)
                .name("Category Test")
                .build();

        given(categoryRepository.findById(any())).willReturn(Optional.of(category));
        given(categoryRepository.findByName(any())).willReturn(null);
//        given(categoryRepository.save(any())).willReturn(category);

        categoryService.updateCategory(categoryUpdateRequest);
        System.out.println("category.getName() = " + category.getName());

        assertThat(category.getName()).isEqualTo("update test");
    }

    @Test
    @DisplayName("카테고리 변경 실패")
    void updateCategoryFailTest1(){
        given(categoryRepository.findByName(any())).willThrow(ApiException.builder().status(HttpStatus.BAD_REQUEST)
                .errorCode(CategoryErrorCode.OVERLAP_CATEGORY.getCode())
                .errorMessage(CategoryErrorCode.OVERLAP_CATEGORY.getMessage())
                .build());

        assertThatThrownBy(() -> {
            categoryService.updateCategory(categoryUpdateRequest);
        }).isInstanceOf(Exception.class).hasMessageContaining(CategoryErrorCode.OVERLAP_CATEGORY.getMessage());
    }

    @Test
    @DisplayName("카테고리 변경 실패2")
    void updateCategoryFailTest2(){
        given(categoryRepository.findByName(any())).willReturn(null);
        given(categoryRepository.findById(any())).willThrow(ApiException.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorMessage(CategoryErrorCode.NOT_FOUND_CATEGORY.getMessage())
                .errorCode(CategoryErrorCode.NOT_FOUND_CATEGORY.getCode())
                .build());

        assertThatThrownBy(() -> {
            categoryService.updateCategory(categoryUpdateRequest);
        }).isInstanceOf(Exception.class).hasMessageContaining(CategoryErrorCode.NOT_FOUND_CATEGORY.getMessage());
    }

    @Test
    @DisplayName("카테고리 삭제 실패")
    void deleteFailTest() {
        given(categoryRepository.findById(any())).willThrow(ApiException.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorMessage(CategoryErrorCode.NOT_FOUND_CATEGORY.getMessage())
                .errorCode(CategoryErrorCode.NOT_FOUND_CATEGORY.getCode())
                .build());

        assertThatThrownBy(() -> {
            categoryService.deleteCategory(1L);
        }).isInstanceOf(Exception.class).hasMessageContaining(CategoryErrorCode.NOT_FOUND_CATEGORY.getMessage());
    }

    @Test
    @DisplayName("카테고리 삭제 성공")
    void deleteSuccessTest(){
        Category category = Category.of()
                .id(1L)
                .name("test code")
                .build();

        given(categoryRepository.findById(any())).willReturn(Optional.of(category));

        categoryService.deleteCategory(1L);

        then(categoryRepository).should(times(1)).delete(any());
        then(categoryRepository).should(times(1)).findById(any());
    }
}