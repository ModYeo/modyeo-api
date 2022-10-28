package com.co.kr.modyeo.api.category.domain.entity;

import com.co.kr.modyeo.common.entity.BaseEntity;
import com.co.kr.modyeo.common.enumerate.Yn;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name")
    private String name;

    @Column(name = "image_path")
    private String imagePath;

    @Enumerated(EnumType.STRING)
    @Column(name = "use_yn")
    private Yn useYn;
    @Builder(builderClassName = "of", builderMethodName = "of")
    public Category(Long id, String name, String imagePath, Yn useYn) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.useYn = useYn;
    }

    @Builder(builderClassName = "createCategoryBuilder", builderMethodName = "createCategoryBuilder")
    public Category(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
        this.useYn = Yn.Y;
    }


    public void changeCategory(String name, Yn useYn, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
        this.useYn = useYn;
    }
}
