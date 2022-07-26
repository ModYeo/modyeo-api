package com.co.kr.modyeo.api.bbs.domain.dto.response;

import com.co.kr.modyeo.api.bbs.domain.entity.TeamArticle;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TeamArticleResponse {
    private Long articleId;

    private String title;

    private String content;

    private String filePath;

    private Yn isHidden;

    private Integer replyCount;

    private Integer recommendCount;

    private Long hitCount;

    private String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;

    @Builder(builderMethodName = "of", builderClassName = "of")
    public TeamArticleResponse(Long articleId,
                               String title,
                               String content,
                               String filePath,
                               Yn isHidden,
                               Integer replyCount,
                               Integer recommendCount,
                               Long hitCount,
                               String createdBy,
                               LocalDateTime createdTime) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.filePath = filePath;
        this.isHidden = isHidden;
        this.replyCount = replyCount;
        this.recommendCount = recommendCount;
        this.hitCount = hitCount;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
    }

    public static TeamArticleResponse toDto(TeamArticle teamArticle){
        return of()
                .articleId(teamArticle.getId())
                .filePath(teamArticle.getFilePath())
                .title(teamArticle.getTitle())
                .content(teamArticle.getContent())
                .isHidden(teamArticle.getIsHidden())
                .hitCount(teamArticle.getHitCount())
                .recommendCount((int) teamArticle.getTeamArticleRecommendList().stream().filter(articleRecommend -> articleRecommend.getRecommendYn() == Yn.Y).count())
                .replyCount(teamArticle.getTeamReplyList().size())
                .createdBy(teamArticle.getCreatedBy())
                .createdTime(teamArticle.getCreatedDate())
                .build();
    }
}
