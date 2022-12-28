package com.co.kr.modyeo.api.columncode.service.impl;

import com.co.kr.modyeo.api.columncode.domain.dto.request.ColumnCodeSearch;
import com.co.kr.modyeo.api.columncode.domain.dto.response.ColumnCodeResponse;
import com.co.kr.modyeo.api.columncode.domain.entity.ColumnCode;
import com.co.kr.modyeo.api.columncode.repository.ColumnCodeRepository;
import com.co.kr.modyeo.api.columncode.service.ColumnCodeService;
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
    @Override
    public Page<ColumnCodeResponse> getColumnCodes(ColumnCodeSearch columnCodeSearch) {
        PageRequest pageRequest = PageRequest.of(columnCodeSearch.getOffset(), columnCodeSearch.getLimit(), columnCodeSearch.getDirection(), columnCodeSearch.getOrderBy());
        return columnCodeRepository.searchColumnCode(columnCodeSearch,pageRequest).map(ColumnCodeResponse::toDto);
    }
}
