package com.co.kr.modyeo.member.controller.api;

import com.co.kr.modyeo.common.exception.code.AuthErrorCode;
import com.co.kr.modyeo.common.exception.code.CategoryErrorCode;
import com.co.kr.modyeo.common.result.JsonResultData;
import com.co.kr.modyeo.member.domain.dto.request.CategoryRequest;
import com.co.kr.modyeo.member.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.member.domain.dto.search.CategorySearch;
import com.co.kr.modyeo.member.domain.entity.Category;
import com.co.kr.modyeo.member.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

}
