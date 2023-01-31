package com.co.kr.modyeo.api.member.domain.dto.response;

import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import com.co.kr.modyeo.api.member.domain.entity.link.MemberActiveArea;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberActiveAreaResponse {

    private Long memberActiveAreaId;

    private Long emdAreaId;

    private String emdAreaName;

    private Integer limitMeters;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public MemberActiveAreaResponse(Long memberActiveAreaId, Long emdAreaId, String emdAreaName, Integer limitMeters) {
        this.memberActiveAreaId = memberActiveAreaId;
        this.emdAreaId = emdAreaId;
        this.emdAreaName = emdAreaName;
        this.limitMeters = limitMeters;
    }

    public static MemberActiveAreaResponse toDto(MemberActiveArea memberActiveArea){
        return of()
                .memberActiveAreaId(memberActiveArea.getId())
                .emdAreaId(memberActiveArea.getEmdArea().getId())
                .emdAreaName(memberActiveArea.getEmdArea().getName())
                .limitMeters(memberActiveArea.getLimitMeters())
                .build();
    }
}
