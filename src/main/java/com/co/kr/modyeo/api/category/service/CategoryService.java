package com.co.kr.modyeo.api.category.service;

import com.co.kr.modyeo.api.category.domain.dto.request.CategoryCreateRequest;
import com.co.kr.modyeo.api.category.domain.dto.request.CategoryUpdateRequest;
import com.co.kr.modyeo.api.category.domain.dto.response.CategoryDetail;
import com.co.kr.modyeo.api.category.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.api.category.domain.dto.search.CategorySearch;
import com.co.kr.modyeo.api.category.domain.entity.Category;

import java.util.List;

public interface CategoryService {
    Long createCategory(CategoryCreateRequest categoryCreateRequest);

    List<CategoryResponse> getCategories(CategorySearch categorySearch);

    Long updateCategory(CategoryUpdateRequest categoryUpdateRequest);

    void deleteCategory(Long categoryId);

    CategoryDetail getCategory(Long categoryId);
}
