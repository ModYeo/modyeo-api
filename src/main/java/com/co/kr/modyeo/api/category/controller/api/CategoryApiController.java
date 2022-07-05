package com.co.kr.modyeo.api.category.controller.api;

import com.co.kr.modyeo.api.category.domain.dto.request.CategoryRequest;
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
    public ResponseEntity<?> create(@Valid @RequestBody CategoryRequest categoryRequest){
        Category category = categoryService.create(categoryRequest);
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

    @GetMapping("")
    public ResponseEntity<?> read(@Valid CategorySearch categorySearch){
        List<CategoryResponse> categoryList = categoryService.read(categorySearch);
        return ResponseEntity
                .ok(JsonResultData
                        .successResultBuilder()
                        .data(categoryList)
                        .build());
    }

    @PatchMapping("")
    public ResponseEntity<?> update(@Valid @RequestBody CategoryRequest categoryRequest){
        Category category = categoryService.update(categoryRequest);
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
    public ResponseEntity<?> delete(@PathVariable(value = "category_id")Long categoryId){
        categoryService.delete(categoryId);
        return ResponseEntity
                .ok(JsonResultData
                        .successResultBuilder()
                        .data(null)
                        .build());
    }
}
