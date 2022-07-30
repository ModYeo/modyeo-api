package com.co.kr.modyeo.api.member.collection.domain.entity;

import com.co.kr.modyeo.api.member.collection.domain.dto.response.CollectionInfoResponse;
import com.co.kr.modyeo.common.entity.BaseEntity;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "COLLECTION_INFO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CollectionInfo extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_info_id")
    private Long id;

    @Column(name = "collection_info_name")
    private String name;

    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "use_yn")
    private Yn useYn;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public CollectionInfo(Long id, String name, String description, Yn useYn) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.useYn = useYn;
    }

    @Builder(builderMethodName = "createCollectionInfoBuilder",builderClassName = "createCollectionInfoBuilder")
    public static CollectionInfo createCollectionInfo(String name, String description){
        return of()
                .name(name)
                .description(description)
                .useYn(Yn.Y)
                .build();
    }

    @Builder(builderClassName = "updateCollectionInfoBuilder",builderMethodName = "updateCollectionInfoBuilder")
    public void changeCollectionInfo(String name, String description, Yn useYn){
        this.name = name;
        this.description = description;
        this.useYn = useYn;
    }
}
