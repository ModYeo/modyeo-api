package com.co.kr.modyeo.api.member.collection.domain.dto.request;

import com.co.kr.modyeo.api.member.collection.domain.entity.CollectionInfo;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
public class CollectionInfoRequest {

    private Long collectionInfoId;

    private String collectionInfoName;

    private String description;

    private Yn useYn;

    public static CollectionInfo toEntity(CollectionInfoRequest collectionInfoRequest){
        return CollectionInfo.createCollectionInfoBuilder()
                .name(collectionInfoRequest.getCollectionInfoName())
                .description(collectionInfoRequest.getDescription())
                .build();
    }
}
