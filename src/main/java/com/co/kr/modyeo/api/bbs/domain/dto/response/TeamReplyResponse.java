package com.co.kr.modyeo.api.bbs.domain.dto.response;

import com.co.kr.modyeo.api.bbs.domain.entity.TeamReply;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    private Long createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public TeamReplyResponse(Long replyId, Long teamArticleId, String content, Integer replyDepth, Long replyGroup, Long createdBy, LocalDateTime createdTime) {
        this.replyId = replyId;
        this.teamArticleId = teamArticleId;
        this.content = content;
        this.replyDepth = replyDepth;
        this.replyGroup = replyGroup;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
    }

    public static TeamReplyResponse toDto(TeamReply reply) {
        return TeamReplyResponse.of()
                .replyId(reply.getId())
                .teamArticleId(reply.getTeamArticle().getId())
                .content(reply.getContent())
                .replyDepth(reply.getReplyDepth())
                .replyGroup(reply.getReplyGroup())
                .createdBy(reply.getCreatedBy())
                .createdTime(reply.getCreatedDate())
                .build();
    }
}
