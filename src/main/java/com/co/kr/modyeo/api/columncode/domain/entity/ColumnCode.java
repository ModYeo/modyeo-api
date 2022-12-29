package com.co.kr.modyeo.api.columncode.domain.entity;

import com.co.kr.modyeo.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "COLUMN_CODE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ColumnCode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "column_code_id")
    private Long id;

    @Column(name = "column_code_name")
    private String name;

    private String code;

    private String description;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public ColumnCode(Long id, String name, String code, String description) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public static ColumnCode create(String name, String code, String description){
        return of()
                .name(name)
                .code(code)
                .description(description)
                .build();
    }
}
