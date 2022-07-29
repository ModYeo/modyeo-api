package com.co.kr.modyeo.api.member.collection.domain.entity;

import com.co.kr.modyeo.api.member.collection.domain.dto.response.CollectionInfoResponse;
import com.co.kr.modyeo.common.entity.BaseEntity;
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

    @Builder(builderClassName = "of", builderMethodName = "of")
    public CollectionInfo(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
