package com.co.kr.modyeo.api.member.domain.dto.response;

import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberCollectionInfoResponse {
    private Long collectionInfoId;

    private String collectionInfoName;

    private String description;

    private Yn agreeYn;

    @Builder(builderMethodName = "of",builderClassName = "of")
    public MemberCollectionInfoResponse(Long collectionInfoId, String collectionInfoName, String description, Yn agreeYn) {
        this.collectionInfoId = collectionInfoId;
        this.collectionInfoName = collectionInfoName;
        this.description = description;
        this.agreeYn = agreeYn;
    }
}
