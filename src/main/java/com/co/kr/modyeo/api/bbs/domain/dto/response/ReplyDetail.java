package com.co.kr.modyeo.api.bbs.domain.dto.response;

import com.co.kr.modyeo.api.bbs.domain.entity.Reply;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ReplyDetail {

    private Long replyId;

    private Long articleId;

    private String content;

    private Integer replyDepth;

    private Long replyGroup;

    private Yn deleteYn;

    private Long createdBy;

    private Long updatedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedTime;

    private List<NestedReplyDetail> nestedReplyDetails;

    @Data
    static class NestedReplyDetail {

        private Long replyId;

        private Long articleId;

        private String content;

        private Integer replyDepth;

        private Long replyGroup;

        private Yn deleteYn;

        private Long createdBy;

        private Long updatedBy;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createdTime;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime updatedTime;

        @Builder(builderClassName = "of", builderMethodName = "of")
        public NestedReplyDetail(Long replyId,
                                 Long articleId,
                                 String content,
                                 Integer replyDepth,
                                 Long replyGroup,
                                 Long createdBy,
                                 Long updatedBy,
                                 Yn deleteYn,
                                 LocalDateTime createdTime,
                                 LocalDateTime updatedTime) {
            this.replyId = replyId;
            this.articleId = articleId;
            this.content = content;
            this.replyDepth = replyDepth;
            this.replyGroup = replyGroup;
            this.createdBy = createdBy;
            this.updatedBy = updatedBy;
            this.deleteYn = deleteYn;
            this.createdTime = createdTime;
            this.updatedTime = updatedTime;
        }

        public static NestedReplyDetail toDto(Reply reply) {
            return of()
                    .replyId(reply.getId())
                    .articleId(reply.getArticle().getId())
                    .content(reply.getContent())
                    .replyDepth(reply.getReplyDepth())
                    .replyGroup(reply.getReplyGroup())
                    .createdBy(reply.getCreatedBy())
                    .updatedBy(reply.getUpdatedBy())
                    .deleteYn(reply.getDeleteYn())
                    .createdTime(reply.getCreatedDate())
                    .updatedTime(reply.getLastModifiedDate())
                    .build();
        }
    }

    @Builder(builderClassName = "of", builderMethodName = "of")
    public ReplyDetail(Long replyId,
                       Long articleId,
                       String content,
                       Integer replyDepth,
                       Long replyGroup,
                       Long createdBy,
                       Long updatedBy,
                       Yn deleteYn,
                       LocalDateTime createdTime,
                       LocalDateTime updatedTime,
                       List<NestedReplyDetail> nestedReplyDetails) {
        this.replyId = replyId;
        this.articleId = articleId;
        this.content = content;
        this.replyDepth = replyDepth;
        this.replyGroup = replyGroup;
        this.deleteYn = deleteYn;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.nestedReplyDetails = nestedReplyDetails;
    }

    public static ReplyDetail toDto(Reply reply, List<Reply> nestedReply) {
        return of()
                .replyId(reply.getId())
                .articleId(reply.getArticle().getId())
                .content(reply.getContent())
                .replyDepth(reply.getReplyDepth())
                .replyGroup(reply.getReplyGroup())
                .deleteYn(reply.getDeleteYn())
                .createdBy(reply.getCreatedBy())
                .updatedBy(reply.getUpdatedBy())
                .createdTime(reply.getCreatedDate())
                .updatedTime(reply.getLastModifiedDate())
                .nestedReplyDetails(nestedReply.stream().map(NestedReplyDetail::toDto).collect(Collectors.toList()))
                .build();
    }
}
