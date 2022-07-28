package com.co.kr.modyeo.api.bbs.domain.dto.request;

import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.api.bbs.domain.entity.Reply;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReplyRequest {

    private Long id;

    private Long articleId;

    private String content;

    private Integer replyDepth;

    private Long replyGroup;

    public static Reply toReply(ReplyRequest replyRequest, Article article) {
        return Reply.createReplyBuilder()
                .article(article)
                .content(replyRequest.getContent())
                .build();
    }

    public static Reply toNestedReply(ReplyRequest replyRequest, Article article) {
        return Reply.createNestedReplyBuilder()
                .article(article)
                .content(replyRequest.getContent())
                .replyGroup(replyRequest.getReplyGroup())
                .build();
    }
}
