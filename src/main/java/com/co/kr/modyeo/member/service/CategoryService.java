package com.co.kr.modyeo.member.service;

import com.co.kr.modyeo.member.domain.dto.request.CategoryRequest;
import com.co.kr.modyeo.member.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.member.domain.dto.search.CategorySearch;
import com.co.kr.modyeo.member.domain.entity.Category;

import java.util.List;

public interface CategoryService {
    Category create(CategoryRequest categoryRequest);

    List<CategoryResponse> read(CategorySearch categorySearch);

    Category update(CategoryRequest categoryUpdateRequest);
}
