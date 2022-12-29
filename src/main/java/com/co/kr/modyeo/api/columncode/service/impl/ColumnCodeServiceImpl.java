package com.co.kr.modyeo.api.columncode.service.impl;

import com.co.kr.modyeo.api.columncode.domain.dto.request.ColumnCodeSearch;
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
}
