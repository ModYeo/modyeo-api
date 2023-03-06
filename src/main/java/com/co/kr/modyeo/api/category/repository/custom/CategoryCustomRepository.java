package com.co.kr.modyeo.api.category.repository.custom;

import com.co.kr.modyeo.api.category.domain.dto.search.CategorySearch;
import com.co.kr.modyeo.api.category.domain.entity.Category;

import java.util.List;

public interface CategoryCustomRepository {
    List<Category> searchCategory(CategorySearch categorySearch);

    List<Category> findByCategoryIds(List<Long> categoryIds);
}
