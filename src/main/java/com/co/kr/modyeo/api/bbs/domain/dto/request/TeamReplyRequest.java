package com.co.kr.modyeo.api.bbs.domain.dto.request;

import com.co.kr.modyeo.api.bbs.domain.entity.TeamArticle;
import com.co.kr.modyeo.api.bbs.domain.entity.TeamReply;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamReplyRequest {

    private Long replyId;

    private Long articleId;

    private String content;

    private Integer replyDepth;

    private Long replyGroup;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public TeamReplyRequest(Long id, Long articleId, String content, Integer replyDepth, Long replyGroup) {
        this.id = id;
        this.articleId = articleId;
        this.content = content;
        this.replyDepth = replyDepth;
        this.replyGroup = replyGroup;
    }

    public static TeamReply toTeamReply(TeamArticle teamArticle, String content) {
        return TeamReply.createTeamReplyBuilder()
                .article(teamArticle)
                .content(content)
                .build();
    }

    public static TeamReply toTeamNestedReply(TeamArticle teamArticle, String content, Long replyGroup) {
        return TeamReply.createNestedTeamReplyBuilder()
                .article(teamArticle)
                .replyGroup(replyGroup)
                .content(content)
                .build();
    }
}
