package com.co.kr.modyeo.api.bbs.domain.entity;

import com.co.kr.modyeo.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    private String content;

    @Column(name = "recommend_count")
    private Long recommendCount;

    @Column(name = "reply_depth")
    private Integer replyDepth;

    @Column(name = "reply_group")
    private Long replyGroup;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public Reply(Long id, Article article, String content, Integer replyDepth, Long replyGroup, Long recommendCount) {
        this.id = id;
        this.article = article;
        this.content = content;
        this.replyDepth = replyDepth;
        this.replyGroup = replyGroup;
        this.recommendCount = recommendCount;
    }

    @Builder(builderClassName = "createReplyBuilder",builderMethodName = "createReplyBuilder")
    public static Reply createReply(Article article,String content){
        return of()
                .article(article)
                .content(content)
                .replyDepth(0)
                .recommendCount(0L)
                .build();
    }

    @Builder(builderClassName = "createNestedReplyBuilder",builderMethodName = "createNestedReplyBuilder")
    public static Reply createNestedReply(Article article, String content, Long replyGroup){
        return of()
                .article(article)
                .content(content)
                .replyDepth(1)
                .replyGroup(replyGroup)
                .recommendCount(0L)
                .build();
    }

    @Builder(builderClassName = "changeReplyBuilder",builderMethodName = "changeReplyBuilder")
    public void changeReply(String content){
        this.content = content;
    }

    public void updateRecommendCount(String operation) {
        if ("plus".equals(operation)){
            this.recommendCount++;
        } else if ("minus".equals(operation)) {
            this.recommendCount--;
        }
    }
}
