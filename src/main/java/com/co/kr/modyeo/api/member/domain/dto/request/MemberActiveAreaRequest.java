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

    private Integer limitMeters;

    public static MemberActiveArea toEntity(Member member, EmdArea emdArea, Integer limitMeters){
        return MemberActiveArea.of()
                .member(member)
                .emdArea(emdArea)
                .limitMeters(limitMeters)
                .build();
    }
}
