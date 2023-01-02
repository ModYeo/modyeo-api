package com.co.kr.modyeo.api.columncode.service.impl;

import com.co.kr.modyeo.api.columncode.domain.dto.request.ColumnCodeCreateRequest;
import com.co.kr.modyeo.api.columncode.domain.dto.request.ColumnCodeSearch;
import com.co.kr.modyeo.api.columncode.domain.dto.request.ColumnCodeUpdateRequest;
import com.co.kr.modyeo.api.columncode.domain.dto.response.ColumnCodeDetail;
import com.co.kr.modyeo.api.columncode.domain.dto.response.ColumnCodeResponse;
import com.co.kr.modyeo.api.columncode.domain.entity.ColumnCode;
import com.co.kr.modyeo.api.columncode.repository.ColumnCodeRepository;
import com.co.kr.modyeo.api.columncode.service.ColumnCodeService;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ColumnCodeServiceImpl implements ColumnCodeService {

    private final ColumnCodeRepository columnCodeRepository;

    private final MemberRepository memberRepository;
    @Override
    public Page<ColumnCodeResponse> getColumnCodes(ColumnCodeSearch columnCodeSearch) {
        PageRequest pageRequest = PageRequest.of(columnCodeSearch.getOffset(), columnCodeSearch.getLimit(), columnCodeSearch.getDirection(), columnCodeSearch.getOrderBy());
        return columnCodeRepository.searchColumnCode(columnCodeSearch,pageRequest).map(ColumnCodeResponse::toDto);
    }

    @Override
    public ColumnCodeDetail getColumnCode(Long columnCodeId) {
        ColumnCode columnCode = columnCodeRepository.findById(columnCodeId).orElseThrow(
                () -> ApiException.builder()
                        .errorCode("COLUMN_CODE_NOT_FOUND")
                        .errorMessage("컬럼코드를 찾을 없습니다.")
                        .build());
        return ColumnCodeDetail.toDto(columnCode);
    }

    @Override
    public Long createColumnCode(ColumnCodeCreateRequest columnCodeCreateRequest) {
        ColumnCode columnCode = ColumnCodeCreateRequest.toDto(columnCodeCreateRequest);
        return columnCodeRepository.save(columnCode).getId();
    }

    @Override
    public Long updateColumnCode(ColumnCodeUpdateRequest columnCodeUpdateRequest) {
        ColumnCode columnCode = columnCodeRepository.findById(columnCodeUpdateRequest.getColumnCodeId()).orElseThrow(
                () -> ApiException.builder()
                        .errorCode("COLUMN_CODE_NOT_FOUND")
                        .errorMessage("컬럼코드를 찾을 없습니다.")
                        .build());

        ColumnCode.updateBuilder()
                .columnCode(columnCode)
                .name(columnCodeUpdateRequest.getColumnCodeName())
                .code(columnCodeUpdateRequest.getCode())
                .description(columnCodeUpdateRequest.getDescription())
                .build();

        return columnCode.getId();
    }

    @Override
    public void deleteColumnCode(Long columnCodeId) {
        ColumnCode columnCode = columnCodeRepository.findById(columnCodeId).orElseThrow(
                () -> ApiException.builder()
                        .errorCode("COLUMN_CODE_NOT_FOUND")
                        .errorMessage("컬럼코드를 찾을 없습니다.")
                        .build());

        columnCodeRepository.delete(columnCode);
    }
}
