package com.co.kr.modyeo.api.columncode.controller;

import com.co.kr.modyeo.api.columncode.domain.dto.request.ColumnCodeSearch;
import com.co.kr.modyeo.api.columncode.domain.dto.response.ColumnCodeDetail;
import com.co.kr.modyeo.api.columncode.domain.dto.response.ColumnCodeResponse;
import com.co.kr.modyeo.api.columncode.domain.entity.ColumnCode;
import com.co.kr.modyeo.api.columncode.service.ColumnCodeService;
import com.co.kr.modyeo.common.result.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/column-code")
@RequiredArgsConstructor
public class ColumnCodeController {

    private final ColumnCodeService columnCodeService;

    @GetMapping("")
    public ResponseEntity<?> getColumnCodes(ColumnCodeSearch columnCodeSearch){
        Page<ColumnCodeResponse> columnCodes = columnCodeService.getColumnCodes(columnCodeSearch);
        return ResponseHandler.generate()
                .data(columnCodes)
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/{column_code_id}")
    public ResponseEntity<?> getColumnCode(@PathVariable(value = "column_code_id") Long columnCodeId){
        ColumnCodeDetail columnCodeDetail = columnCodeService.getColumnCode(columnCodeId);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .build();
    }
}
