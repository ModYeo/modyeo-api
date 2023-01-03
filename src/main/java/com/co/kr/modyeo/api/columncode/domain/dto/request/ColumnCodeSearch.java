package com.co.kr.modyeo.api.columncode.domain.dto.request;

import com.co.kr.modyeo.common.dto.SearchDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class ColumnCodeSearch extends SearchDto {

    private String name;

    private String code;

    public ColumnCodeSearch(String name, String code, Integer limit, Integer offset, String orderBy, Sort.Direction direction) {
        super(limit, offset, orderBy, direction);
        this.name = name;
        this.code = code;
    }
}
