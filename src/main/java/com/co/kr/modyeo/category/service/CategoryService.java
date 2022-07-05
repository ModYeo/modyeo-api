package com.co.kr.modyeo.category.service;

import com.co.kr.modyeo.category.domain.dto.request.CategoryRequest;
import com.co.kr.modyeo.category.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.category.domain.dto.search.CategorySearch;
import com.co.kr.modyeo.category.domain.entity.Category;

import java.util.List;

public interface CategoryService {
    Category create(CategoryRequest categoryRequest);

    List<CategoryResponse> read(CategorySearch categorySearch);

    Category update(CategoryRequest categoryUpdateRequest);

    void delete(Long categoryId);
}
