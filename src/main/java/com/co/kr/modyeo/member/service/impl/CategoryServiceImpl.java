package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.AuthErrorCode;
import com.co.kr.modyeo.common.exception.code.CategoryErrorCode;
import com.co.kr.modyeo.member.domain.dto.request.CategoryRequest;
import com.co.kr.modyeo.member.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.member.domain.dto.search.CategorySearch;
import com.co.kr.modyeo.member.domain.entity.Category;
import com.co.kr.modyeo.member.repository.CategoryRepository;
import com.co.kr.modyeo.member.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Category create(CategoryRequest categoryRequest) {
        overlapCategoryCheck(categoryRequest);
        Category category = categoryRequest.toEntity();
        return categoryRepository.save(category);
    }

    @Override
    public List<CategoryResponse> read(CategorySearch categorySearch) {
        List<Category> categoryList = categoryRepository.searchCategory(categorySearch);
        return categoryList.stream().map(CategoryResponse::toRes).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Category update(CategoryRequest categoryUpdateRequest) {
        overlapCategoryCheck(categoryUpdateRequest);
        Category category = categoryRepository.findById(categoryUpdateRequest.getId())
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(CategoryErrorCode.NOT_FOUND_CATEGORY.getMessage())
                        .errorCode(CategoryErrorCode.NOT_FOUND_CATEGORY.getCode())
                        .build());

        category.changeCategory(categoryUpdateRequest.getName());
        return category;
    }

    private void overlapCategoryCheck(CategoryRequest categoryRequest){
        Category findCategory = categoryRepository.findByName(categoryRequest.getName());
        if (findCategory != null){
            throw ApiException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .errorCode(CategoryErrorCode.OVERLAP_CATEGORY.getCode())
                    .errorMessage(CategoryErrorCode.OVERLAP_CATEGORY.getMessage())
                    .build();

        }
    }
}
