package com.co.kr.modyeo.api.category.domain.entity;

import com.co.kr.modyeo.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "CATEGORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name")
    private String name;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void changeCategory(String name){
        this.name = name;
    }
}
