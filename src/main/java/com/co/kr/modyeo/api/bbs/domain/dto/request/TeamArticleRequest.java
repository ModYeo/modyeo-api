package com.co.kr.modyeo.api.bbs.domain.dto.request;

import com.co.kr.modyeo.api.bbs.domain.entity.TeamArticle;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamArticleRequest {
    private Long articleId;

    private Long teamId;

    private String title;

    private String content;

    private String filePath;

    private Long hitCount;

    private Yn isHidden;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public TeamArticleRequest(Long articleId, String title, String content, String filePath, Long hitCount, Yn isHidden) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.filePath = filePath;
        this.hitCount = hitCount;
        this.isHidden = isHidden;
    }

    public static TeamArticle toEntity(TeamArticleRequest articleRequest){
        return TeamArticle.of()
                .id(articleRequest.articleId)
                .title(articleRequest.title)
                .content(articleRequest.content)
                .filePath(articleRequest.filePath)
                .hitCount(articleRequest.hitCount)
                .build();
    }

    public static TeamArticle createArticle(TeamArticleRequest articleRequest, Team team){
        return TeamArticle.createTeamArticleBuilder()
                .content(articleRequest.getContent())
                .team(team)
                .title(articleRequest.getTitle())
                .filePath(articleRequest.getFilePath())
                .isHidden(articleRequest.getIsHidden())
                .build();
    }
}
