package com.co.kr.modyeo.api.columncode.repository.custom;

import com.co.kr.modyeo.api.columncode.domain.dto.request.ColumnCodeSearch;
import com.co.kr.modyeo.api.columncode.domain.entity.ColumnCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ColumnCodeCustomRepository {
    Page<ColumnCode> searchColumnCode(ColumnCodeSearch columnCodeSearch, Pageable pageRequest);
}
