package com.co.kr.modyeo.api.bbs.domain.dto.request;

import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.api.bbs.domain.entity.Reply;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ReplyCreateRequest {

    @NotNull
    private Long articleId;

    private String content;

    @NotNull
    private Integer replyDepth;

    private Long replyGroup;

    @Builder(builderMethodName = "of", builderClassName = "of")
    public ReplyCreateRequest(Long articleId, String content, Integer replyDepth, Long replyGroup) {
        this.articleId = articleId;
        this.content = content;
        this.replyDepth = replyDepth;
        this.replyGroup = replyGroup;
    }

    public static Reply toReply(ReplyCreateRequest replyCreateRequest, Article article) {
        return Reply.createReplyBuilder()
                .article(article)
                .content(replyCreateRequest.getContent())
                .build();
    }

    public static Reply toNestedReply(ReplyCreateRequest replyCreateRequest, Article article) {
        return Reply.createNestedReplyBuilder()
                .article(article)
                .content(replyCreateRequest.getContent())
                .replyGroup(replyCreateRequest.getReplyGroup())
                .replyDepth(replyCreateRequest.getReplyDepth())
                .build();
    }
}
