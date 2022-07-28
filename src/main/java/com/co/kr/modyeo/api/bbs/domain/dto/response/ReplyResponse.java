package com.co.kr.modyeo.api.bbs.domain.dto.response;

import com.co.kr.modyeo.api.bbs.domain.entity.Reply;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReplyResponse {

    private Long replyId;

    private Long articleId;

    private String content;

    private Integer replyDepth;

    private Long replyGroup;

    private String createBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public ReplyResponse(Long replyId, Long articleId, String content, Integer replyDepth, Long replyGroup, String createBy, LocalDateTime createdTime) {
        this.replyId = replyId;
        this.articleId = articleId;
        this.content = content;
        this.replyDepth = replyDepth;
        this.replyGroup = replyGroup;
        this.createBy = createBy;
        this.createdTime = createdTime;
    }

    public static ReplyResponse toDto(Reply reply) {
        return ReplyResponse.of()
                .replyId(reply.getId())
                .articleId(reply.getArticle().getId())
                .content(reply.getContent())
                .replyDepth(reply.getReplyDepth())
                .replyGroup(reply.getReplyGroup())
                .createBy(reply.getCreatedBy())
                .createdTime(reply.getCreatedDate())
                .build();
    }
}
