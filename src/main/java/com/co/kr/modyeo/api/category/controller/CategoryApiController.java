package com.co.kr.modyeo.api.category.controller;

import com.co.kr.modyeo.api.category.domain.dto.request.CategoryRequest;
import com.co.kr.modyeo.api.category.domain.dto.response.CategoryDetail;
import com.co.kr.modyeo.api.category.domain.dto.search.CategorySearch;
import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.category.service.CategoryService;
import com.co.kr.modyeo.common.exception.code.CategoryErrorCode;
import com.co.kr.modyeo.common.result.JsonResultData;
import com.co.kr.modyeo.api.category.domain.dto.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        Category category = categoryService.createCategory(categoryRequest);
        if (category != null){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(null);
        }else{
            return ResponseEntity
                    .badRequest()
                    .body(JsonResultData.failResultBuilder()
                            .errorCode(CategoryErrorCode.FAIL_CREATE_CATEGORY.getCode())
                            .errorMessage(CategoryErrorCode.FAIL_CREATE_CATEGORY.getMessage()));
        }
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<?> getCategory(
            @PathVariable(value = "category_id")Long categoryId){
        CategoryDetail categoryDetail = categoryService.getCategory(categoryId);
        return ResponseEntity
                .ok(JsonResultData
                        .successResultBuilder()
                        .data(categoryDetail)
                        .build());
    }

    @GetMapping("")
    public ResponseEntity<?> getCategories(@Valid CategorySearch categorySearch){
        List<CategoryResponse> categoryList = categoryService.getCategories(categorySearch);
        return ResponseEntity
                .ok(JsonResultData
                        .successResultBuilder()
                        .data(categoryList)
                        .build());
    }

    @PatchMapping("")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        Category category = categoryService.updateCategory(categoryRequest);
        if (Objects.equals(categoryRequest.getName(), category.getName())){
            return ResponseEntity
                    .ok(JsonResultData
                            .successResultBuilder()
                            .data(null)
                            .build());
        }else{
            return ResponseEntity
                    .ok(JsonResultData
                            .failResultBuilder()
                            .errorCode(CategoryErrorCode.FAIL_UPDATE_CATEGORY.getCode())
                            .errorMessage(CategoryErrorCode.FAIL_UPDATE_CATEGORY.getMessage())
                            .build());
        }
    }

    @DeleteMapping("/{category_id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(value = "category_id")Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity
                .ok(JsonResultData
                        .successResultBuilder()
                        .data(null)
                        .build());
    }
}
