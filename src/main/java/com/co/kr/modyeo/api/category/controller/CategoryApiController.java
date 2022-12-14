package com.co.kr.modyeo.api.category.controller;

import com.co.kr.modyeo.api.category.domain.dto.request.CategoryCreateRequest;
import com.co.kr.modyeo.api.category.domain.dto.request.CategoryUpdateRequest;
import com.co.kr.modyeo.api.category.domain.dto.response.CategoryDetail;
import com.co.kr.modyeo.api.category.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.api.category.domain.dto.search.CategorySearch;
import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.category.service.CategoryService;
import com.co.kr.modyeo.common.exception.code.CategoryErrorCode;
import com.co.kr.modyeo.common.result.JsonResultData;
import com.co.kr.modyeo.common.result.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@Api("카테고리 API Controller")
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;

    @ApiOperation(value = "카테고리 생성 API")
    @PostMapping("")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryCreateRequest categoryCreateRequest) {
        Long categoryId = categoryService.createCategory(categoryCreateRequest);
        return ResponseHandler.generate()
                .data(categoryId)
                .status(HttpStatus.CREATED)
                .build();
    }

    @ApiOperation(value = "카테고리 상세 조회 API")
    @GetMapping("/{category_id}")
    public ResponseEntity<?> getCategory(
            @PathVariable(value = "category_id") Long categoryId) {
        CategoryDetail categoryDetail = categoryService.getCategory(categoryId);
        return ResponseHandler.generate()
                .data(categoryDetail)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "카테고리 리스트 조회 API")
    @GetMapping("")
    public ResponseEntity<?> getCategories(@Valid CategorySearch categorySearch) {
        List<CategoryResponse> categoryList = categoryService.getCategories(categorySearch);
        return ResponseHandler.generate()
                .data(categoryList)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "카테고리 수정 API")
    @PatchMapping("")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        Long categoryId = categoryService.updateCategory(categoryUpdateRequest);
        return ResponseHandler.generate()
                .data(categoryId)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "카테고리 삭제 API")
    @DeleteMapping("/{category_id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(value = "category_id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseHandler.generate()
                .status(HttpStatus.NO_CONTENT)
                .data(null)
                .build();
    }
}
