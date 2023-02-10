package com.co.kr.modyeo.api.bbs.domain.dto.response;

import com.co.kr.modyeo.api.bbs.domain.entity.link.TeamArticleRecommend;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamArticleRecommendResponse {


    private Long articleRecommendId;

    private Long articleId;

    private Long memberId;

    private Yn recommendYn;

    @Builder(builderMethodName = "of", builderClassName = "of")
    public TeamArticleRecommendResponse(Long articleRecommendId, Long articleId, Long memberId, Yn recommendYn) {
        this.articleRecommendId = articleRecommendId;
        this.articleId = articleId;
        this.memberId = memberId;
        this.recommendYn = recommendYn;
    }

    public static TeamArticleRecommendResponse toDto(TeamArticleRecommend articleRecommend){
        return of()
                .articleRecommendId(articleRecommend.getId())
                .articleId(articleRecommend.getTeamArticle().getId())
                .memberId(articleRecommend.getMember().getId())
                .recommendYn(articleRecommend.getRecommendYn())
                .build();

    }
}
