package com.co.kr.modyeo.api.member.domain.entity.link;

import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "MEMBER_ACTIVE_AREA")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberActiveArea extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_active_area_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emd_area_id")
    private EmdArea emdArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "limit_meters")
    private Integer limitMeters;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public MemberActiveArea(Long id, EmdArea emdArea, Member member, Integer limitMeters) {
        this.id = id;
        this.emdArea = emdArea;
        this.member = member;
        this.limitMeters = limitMeters;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public static MemberActiveArea create(EmdArea emdArea, Member member, Integer distanceMeters){
        return of()
                .emdArea(emdArea)
                .member(member)
                .limitMeters(distanceMeters)
                .build();
    }

    public static void changeLimitMeters(MemberActiveArea memberActiveArea, Integer limitMeters) {
        memberActiveArea.limitMeters = limitMeters;
    }
}
