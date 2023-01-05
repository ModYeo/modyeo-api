package com.co.kr.modyeo.api.columncode.domain.dto.request;

import com.co.kr.modyeo.api.columncode.domain.entity.ColumnCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class ColumnCodeCreateRequest {

    @NotBlank(message = "Column Code Name은 공백일 수 없습니다.")
    private String columnCodeName;

    @Length(min = 1, max = 10, message = "CODE의 경우 1글자 이상 10글자 이하여야만 합니다.")
    private String code;

    private String description;

    public static ColumnCode toDto(ColumnCodeCreateRequest columnCodeCreateRequest) {
        return ColumnCode
                .createBuilder()
                .name(columnCodeCreateRequest.getColumnCodeName())
                .code(columnCodeCreateRequest.getCode())
                .description(columnCodeCreateRequest.getDescription())
                .build();
    }
}
