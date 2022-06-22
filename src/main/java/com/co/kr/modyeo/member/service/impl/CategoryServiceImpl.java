package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.member.domain.dto.request.CategoryRequest;
import com.co.kr.modyeo.member.domain.entity.Category;
import com.co.kr.modyeo.member.repository.CategoryRepository;
import com.co.kr.modyeo.member.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void create(CategoryRequest categoryRequest) {
        overlapCategoryCheck(categoryRequest);
        Category category = categoryRequest.toEntity();
        categoryRepository.save(category);
    }

    private void overlapCategoryCheck(CategoryRequest categoryRequest){
        Category findCategory = categoryRepository.findByName(categoryRequest.getName());
        if (findCategory != null){
            throw new RuntimeException("카테고리 이름 중복");
        }
    }
}
