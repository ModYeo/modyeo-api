package com.co.kr.modyeo.api.bbs.domain.entity.link;

import com.co.kr.modyeo.api.bbs.domain.entity.TeamArticle;
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
@Table(name = "TEAM_ARTICLE_RECOMMEND")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamArticleRecommend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_article_recommend_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_article_id")
    private TeamArticle teamArticle;

    @Column(name = "recommend_yn")
    @Enumerated(value = EnumType.STRING)
    private Yn recommendYn;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public TeamArticleRecommend(Long id, Member member, TeamArticle teamArticle, Yn recommendYn) {
        this.id = id;
        this.member = member;
        this.teamArticle = teamArticle;
        this.recommendYn = recommendYn;
    }

    @Builder(builderClassName = "createRecommendBuilder", builderMethodName = "createRecommendBuilder")
    public static TeamArticleRecommend createRecommend(Member member, TeamArticle teamArticle) {
        return of()
                .member(member)
                .teamArticle(teamArticle)
                .recommendYn(Yn.Y)
                .build();
    }

    public void changeRecommendYn(Yn recommendYn) {
        this.recommendYn = recommendYn;
    }
}
