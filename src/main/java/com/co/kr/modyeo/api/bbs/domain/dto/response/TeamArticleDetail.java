package com.co.kr.modyeo.api.bbs.domain.dto.response;

import com.co.kr.modyeo.api.bbs.domain.entity.TeamArticle;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TeamArticleDetail {

    private Long articleId;

    private String title;

    private String content;

    private Yn isHidden;

    private Long hitCount;

    private Integer recommendCount;

    private String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;

    private List<TeamReplyResponse> replyResponses = new ArrayList<>();

    @Builder(builderClassName = "of", builderMethodName = "of")
    public TeamArticleDetail(Long articleId,
                             String title,
                             String content,
                             Yn isHidden,
                             Long hitCount,
                             Integer recommendCount,
                             String createdBy,
                             LocalDateTime createdTime,
                             List<TeamReplyResponse> replyResponses) {
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
                .recommendCount(teamArticle.getTeamArticleRecommendList().size())
                .createdBy(teamArticle.getCreatedBy())
                .createdTime(teamArticle.getCreatedDate())
                .replyResponses(teamArticle.getTeamReplyList().stream().map(TeamReplyResponse::toDto).collect(Collectors.toList()))
                .build();
    }
}
