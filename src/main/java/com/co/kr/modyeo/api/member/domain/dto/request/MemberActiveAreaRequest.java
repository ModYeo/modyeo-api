package com.co.kr.modyeo.api.member.domain.dto.request;

import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.domain.entity.link.MemberActiveArea;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberActiveAreaRequest {

    private Long memberId;

    private Long emdAreaId;

    private Integer distanceMeters;

    public static MemberActiveArea toEntity(Member member, EmdArea emdArea, Integer distanceMeters){
        return MemberActiveArea.of()
                .member(member)
                .emdArea(emdArea)
                .distanceMeters(distanceMeters)
                .build();
    }
}
