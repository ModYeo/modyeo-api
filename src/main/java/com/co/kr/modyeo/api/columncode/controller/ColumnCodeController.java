package com.co.kr.modyeo.api.columncode.controller;

import com.co.kr.modyeo.api.columncode.domain.dto.request.ColumnCodeCreateRequest;
import com.co.kr.modyeo.api.columncode.domain.dto.request.ColumnCodeSearch;
import com.co.kr.modyeo.api.columncode.domain.dto.request.ColumnCodeUpdateRequest;
import com.co.kr.modyeo.api.columncode.domain.dto.response.ColumnCodeDetail;
import com.co.kr.modyeo.api.columncode.domain.dto.response.ColumnCodeResponse;
import com.co.kr.modyeo.api.columncode.service.ColumnCodeService;
import com.co.kr.modyeo.common.result.ResponseHandler;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/column-code")
@RequiredArgsConstructor
public class ColumnCodeController {

    private final ColumnCodeService columnCodeService;

    @ApiOperation(value = "컬럼코드 목록 조회 API(어드민)")
    @GetMapping("")
    public ResponseEntity<?> getColumnCodes(ColumnCodeSearch columnCodeSearch){
        Page<ColumnCodeResponse> columnCodes = columnCodeService.getColumnCodes(columnCodeSearch);
        return ResponseHandler.generate()
                .data(columnCodes)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "컬럼코드 상세조회 API(어드민)")
    @GetMapping("/{column_code_id}")
    public ResponseEntity<?> getColumnCode(@PathVariable(value = "column_code_id") Long columnCodeId){
        ColumnCodeDetail columnCodeDetail = columnCodeService.getColumnCode(columnCodeId);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(columnCodeDetail)
                .build();
    }

    @ApiOperation(value = "컬럼코드 생성 API(어드민)")
    @PostMapping("")
    public ResponseEntity<?> createColumnCode(@Valid @RequestBody ColumnCodeCreateRequest columnCodeCreateRequest){
        Long columnCodeId = columnCodeService.createColumnCode(columnCodeCreateRequest);
        return ResponseHandler.generate()
                .status(HttpStatus.CREATED)
                .data(columnCodeId)
                .build();
    }

    @ApiOperation(value = "컬럼코드 수정 API(어드민)")
    @PatchMapping("")
    public ResponseEntity<?> updateColumnCode(@RequestBody ColumnCodeUpdateRequest columnCodeUpdateRequest){
        Long columnCodeId = columnCodeService.updateColumnCode(columnCodeUpdateRequest);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(columnCodeId)
                .build();
    }

    @ApiOperation(value = "컬럼코드 삭제 API(어드민)")
    @DeleteMapping("/{column_code_id}")
    public ResponseEntity<?> deleteColumnCode(@PathVariable(value = "column_code_id") Long columnCodeId){
        columnCodeService.deleteColumnCode(columnCodeId);
        return ResponseHandler.generate()
                .status(HttpStatus.NO_CONTENT)
                .data(null)
                .build();
    }
}
