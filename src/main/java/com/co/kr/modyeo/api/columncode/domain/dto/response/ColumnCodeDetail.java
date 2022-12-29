package com.co.kr.modyeo.api.columncode.domain.dto.response;

import com.co.kr.modyeo.api.columncode.domain.entity.ColumnCode;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ColumnCodeDetail {

    private Long columnCodeId;

    private String columnCodeName;

    private String code;

    private String description;

    private String email;

    private LocalDateTime createdTime;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public ColumnCodeDetail(Long columnCodeId, String columnCodeName, String code, String description, String email, LocalDateTime createdTime) {
        this.columnCodeId = columnCodeId;
        this.columnCodeName = columnCodeName;
        this.code = code;
        this.description = description;
        this.email = email;
        this.createdTime = createdTime;
    }

    public static ColumnCodeDetail toDto(ColumnCode columnCode) {
        return of()
                .columnCodeId(columnCode.getId())
                .columnCodeName(columnCode.getName())
                .code(columnCode.getCode())
                .description(columnCode.getDescription())
                .createdTime(columnCode.getCreatedDate())
                .build();
    }
}
