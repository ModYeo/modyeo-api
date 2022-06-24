package com.co.kr.modyeo.member.service;

import com.co.kr.modyeo.member.domain.dto.request.CategoryRequest;
import com.co.kr.modyeo.member.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.member.domain.dto.search.CategorySearch;

import java.util.List;

public interface CategoryService {
    void create(CategoryRequest categoryRequest);

    List<CategoryResponse> read(CategorySearch categorySearch);
}
