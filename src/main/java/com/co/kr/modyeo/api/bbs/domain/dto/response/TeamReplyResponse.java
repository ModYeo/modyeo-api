package com.co.kr.modyeo.api.bbs.domain.dto.response;

import com.co.kr.modyeo.api.bbs.domain.entity.TeamReply;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TeamReplyResponse {
    private Long replyId;

    private Long teamArticleId;

    private String content;

    private Integer replyDepth;

    private Long replyGroup;

    private String createBy;

    private LocalDateTime createdTime;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public TeamReplyResponse(Long replyId, Long teamArticleId, String content, Integer replyDepth, Long replyGroup, String createBy, LocalDateTime createdTime) {
        this.replyId = replyId;
        this.teamArticleId = teamArticleId;
        this.content = content;
        this.replyDepth = replyDepth;
        this.replyGroup = replyGroup;
        this.createBy = createBy;
        this.createdTime = createdTime;
    }

    public static TeamReplyResponse toDto(TeamReply reply){
        return TeamReplyResponse.of()
                .replyId(reply.getId())
                .teamArticleId(reply.getTeamArticle().getId())
                .content(reply.getContent())
                .replyDepth(reply.getReplyDepth())
                .replyGroup(reply.getReplyGroup())
                .createBy(reply.getCreatedBy())
                .createdTime(reply.getCreatedDate())
                .build();
    }
}
