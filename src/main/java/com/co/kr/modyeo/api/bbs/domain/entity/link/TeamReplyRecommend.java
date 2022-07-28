package com.co.kr.modyeo.api.bbs.domain.entity.link;

import com.co.kr.modyeo.api.bbs.domain.entity.TeamReply;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.common.entity.BaseEntity;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "TEAM_REPLY_RECOMMEND")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamReplyRecommend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_reply_recommend_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_reply_id")
    private TeamReply teamReply;

    @Column(name = "recommend_yn")
    @Enumerated(value = EnumType.STRING)
    private Yn recommendYn;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public TeamReplyRecommend(Long id, Member member, TeamReply teamReply, Yn recommendYn) {
        this.id = id;
        this.member = member;
        this.teamReply = teamReply;
        this.recommendYn = recommendYn;
    }

    @Builder(builderClassName = "createRecommendBuilder", builderMethodName = "createRecommendBuilder")
    public static TeamReplyRecommend createRecommend(Member member, TeamReply teamReply) {
        return of()
                .member(member)
                .teamReply(teamReply)
                .recommendYn(Yn.Y)
                .build();
    }

    public void changeRecommendYn(Yn recommendYn) {
        this.recommendYn = recommendYn;
    }
}
