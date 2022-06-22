package com.co.kr.modyeo.member.controller.api;

import com.co.kr.modyeo.member.domain.dto.request.CategoryRequest;
import com.co.kr.modyeo.member.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody CategoryRequest categoryRequest){
        categoryService.create(categoryRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(null);
    }
}
