package com.co.kr.modyeo.api.columncode.domain.dto.response;

import com.co.kr.modyeo.api.columncode.domain.entity.ColumnCode;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ColumnCodeResponse {

    private Long columnCodeId;

    private String columnCodeName;

    private String code;

    private String description;

    @Builder(builderMethodName = "of",builderClassName = "of")
    public ColumnCodeResponse(Long columnCodeId, String columnCodeName, String code, String description) {
        this.columnCodeId = columnCodeId;
        this.columnCodeName = columnCodeName;
        this.code = code;
        this.description = description;
    }

    public static ColumnCodeResponse toDto(ColumnCode columnCode){
        return of()
                .columnCodeId(columnCode.getId())
                .columnCodeName(columnCode.getName())
                .code(columnCode.getCode())
                .description(columnCode.getDescription())
                .build();
    }
}
