package com.co.kr.modyeo.member.repository.custom;

import com.co.kr.modyeo.member.domain.dto.search.CategorySearch;
import com.co.kr.modyeo.member.domain.entity.Category;

import java.util.List;

public interface CategoryCustomRepository {
    List<Category> searchCategory(CategorySearch categorySearch);
}
