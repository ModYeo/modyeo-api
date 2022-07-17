package com.co.kr.modyeo.api.bbs.domain.dto.response;

import com.co.kr.modyeo.api.bbs.domain.entity.TeamArticle;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TeamArticleDetail {

    private Long articleId;

    private String title;

    private String content;

    private Yn isHidden;

    private Long hitCount;

    private Long recommendCount;

    private String createdBy;

    private LocalDateTime createdTime;

    private List<ReplyResponse> replyResponses = new ArrayList<>();

    @Builder(builderClassName = "of",builderMethodName = "of")
    public TeamArticleDetail(Long articleId, String title, String content, Yn isHidden, Long hitCount, Long recommendCount, String createdBy, LocalDateTime createdTime, List<ReplyResponse> replyResponses) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.isHidden = isHidden;
        this.hitCount = hitCount;
        this.recommendCount = recommendCount;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.replyResponses = replyResponses;
    }

    public static TeamArticleDetail toDto(TeamArticle teamArticle) {
        return TeamArticleDetail.of()
                .articleId(teamArticle.getId())
                .title(teamArticle.getTitle())
                .content(teamArticle.getContent())
                .isHidden(teamArticle.getIsHidden())
                .hitCount(teamArticle.getHitCount())
                .recommendCount(teamArticle.getRecommendCount())
                .createdBy(teamArticle.getCreatedBy())
                .createdTime(teamArticle.getCreatedDate())
                .build();
    }
}
