package com.co.kr.modyeo.api.category.service;

import com.co.kr.modyeo.api.category.domain.dto.response.CategoryDetail;
import com.co.kr.modyeo.api.category.domain.dto.search.CategorySearch;
import com.co.kr.modyeo.api.category.domain.dto.request.CategoryRequest;
import com.co.kr.modyeo.api.category.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.api.category.domain.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryRequest categoryRequest);

    List<CategoryResponse> getCategories(CategorySearch categorySearch);

    Category updateCategory(CategoryRequest categoryUpdateRequest);

    void deleteCategory(Long categoryId);

    CategoryDetail getCategory(Long categoryId);
}
