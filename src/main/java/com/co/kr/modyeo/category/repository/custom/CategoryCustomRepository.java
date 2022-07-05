package com.co.kr.modyeo.category.repository.custom;

import com.co.kr.modyeo.category.domain.dto.search.CategorySearch;
import com.co.kr.modyeo.category.domain.entity.Category;

import java.util.List;

public interface CategoryCustomRepository {
    List<Category> searchCategory(CategorySearch categorySearch);
}
