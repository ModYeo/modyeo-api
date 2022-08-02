package com.co.kr.modyeo.api.member.collection.domain.dto.response;

import com.co.kr.modyeo.api.member.collection.domain.entity.CollectionInfo;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CollectionInfoResponse {

    private Long collectionInfoId;

    private String collectionInfoName;

    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;

    private String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedTime;

    private String updatedBy;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public CollectionInfoResponse(Long collectionInfoId, String collectionInfoName, String description,LocalDateTime createdTime, String createdBy, LocalDateTime updatedTime, String updatedBy) {
        this.collectionInfoId = collectionInfoId;
        this.collectionInfoName = collectionInfoName;
        this.description = description;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.updatedTime = updatedTime;
        this.updatedBy = updatedBy;
    }

    public static CollectionInfoResponse toDto(CollectionInfo collectionInfo) {
        return of()
                .collectionInfoId(collectionInfo.getId())
                .collectionInfoName(collectionInfo.getName())
                .description(collectionInfo.getDescription())
                .createdTime(collectionInfo.getCreatedDate())
                .createdBy(collectionInfo.getCreatedBy())
                .updatedTime(collectionInfo.getLastModifiedDate())
                .updatedBy(collectionInfo.getUpdatedBy())
                .build();
    }
}
