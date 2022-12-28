package com.co.kr.modyeo.api.columncode.service;

import com.co.kr.modyeo.api.columncode.domain.dto.request.ColumnCodeSearch;
import com.co.kr.modyeo.api.columncode.domain.dto.response.ColumnCodeResponse;
import org.springframework.data.domain.Page;

public interface ColumnCodeService {
    Page<ColumnCodeResponse> getColumnCodes(ColumnCodeSearch columnCodeSearch);
}
