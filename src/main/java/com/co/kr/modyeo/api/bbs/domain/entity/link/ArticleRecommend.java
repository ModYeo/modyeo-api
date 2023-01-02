package com.co.kr.modyeo.api.bbs.domain.entity.link;

import com.co.kr.modyeo.api.bbs.domain.entity.Article;
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
@Table(name = "ARTICLE_RECOMMEND")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleRecommend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_recommend_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(name = "recommend_yn")
    @Enumerated(value = EnumType.STRING)
    private Yn recommendYn;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public ArticleRecommend(Long id, Member member, Article article, Yn recommendYn) {
        this.id = id;
        this.member = member;
        this.article = article;
        this.recommendYn = recommendYn;
    }

    @Builder(builderClassName = "createArticleRecommendBuilder", builderMethodName = "createArticleRecommendBuilder")
    public static ArticleRecommend createArticleRecommend(Member member, Article article) {
        return of()
                .member(member)
                .article(article)
                .recommendYn(Yn.Y)
                .build();
    }

    public void changeRecommendYn(Yn recommendYn) {
        this.recommendYn = recommendYn;
    }
}
