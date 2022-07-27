package com.co.kr.modyeo.api.bbs.domain.entity.link;

import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.api.bbs.domain.entity.Reply;
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
@Table(name = "REPLY_RECOMMEND")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReplyRecommend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_recommend_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_id")
    private Reply reply;

    @Column(name = "recommend_yn")
    @Enumerated(value = EnumType.STRING)
    private Yn recommendYn;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public ReplyRecommend(Long id, Member member, Reply reply, Yn recommendYn) {
        this.id = id;
        this.member = member;
        this.reply = reply;
        this.recommendYn = recommendYn;
    }

    @Builder(builderClassName = "createReplyRecommendBuilder",builderMethodName = "createReplyRecommendBuilder")
    public static ReplyRecommend createReplyRecommend(Member member, Reply reply){
        return of()
                .member(member)
                .reply(reply)
                .build();
    }

    public void changeRecommend(Yn recommendYn){
        this.recommendYn = recommendYn;
    }
}
