package com.co.kr.modyeo.api.advertisement.domain.entity;

import com.co.kr.modyeo.api.advertisement.domain.enumerate.AdvertisementType;
import com.co.kr.modyeo.common.entity.BaseEntity;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "ADVERTISEMENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Advertisement extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advertisement_id")
    private Long id;

    @Column(name = "advertisement_name")
    private String name;

    @Column(name = "url_link")
    private String urlLink;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "advertisement_type")
    @Enumerated(value = EnumType.STRING)
    private AdvertisementType type;

    @Column(name = "use_yn")
    private Yn useYn;


    @Builder(builderClassName = "of",builderMethodName = "of")
    public Advertisement(Long id, String name, String urlLink, String imagePath, AdvertisementType type, Yn useYn) {
        this.id = id;
        this.name = name;
        this.urlLink = urlLink;
        this.imagePath = imagePath;
        this.type = type;
        this.useYn = useYn;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public Advertisement(String name, String urlLink, String imagePath, AdvertisementType type) {
        this.name = name;
        this.urlLink = urlLink;
        this.imagePath = imagePath;
        this.type = type;
        this.useYn = Yn.Y;
    }
}
