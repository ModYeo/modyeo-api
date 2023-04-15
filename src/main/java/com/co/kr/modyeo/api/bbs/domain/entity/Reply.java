package com.co.kr.modyeo.api.bbs.domain.entity;

import com.co.kr.modyeo.api.bbs.domain.entity.link.ReplyRecommend;
import com.co.kr.modyeo.common.entity.BaseEntity;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Lob
    private String content;

    @Column(name = "reply_depth")
    private Integer replyDepth;

    @Column(name = "reply_group")
    private Long replyGroup;

    @Column(name = "delete_yn")
    @Enumerated(value = EnumType.STRING)
    private Yn deleteYn;

    @OneToMany(mappedBy = "reply", cascade = CascadeType.ALL)
    private List<ReplyRecommend> replyRecommendList = new ArrayList<>();

    @Builder(builderClassName = "of", builderMethodName = "of")
    public Reply(Long id, Article article, String content, Integer replyDepth, Long replyGroup, List<ReplyRecommend> replyRecommendList, Yn deleteYn) {
        this.id = id;
        this.article = article;
        this.content = content;
        this.replyDepth = replyDepth;
        this.replyGroup = replyGroup;
        this.deleteYn = deleteYn;
        this.replyRecommendList = replyRecommendList;
    }

    @Builder(builderClassName = "createReplyBuilder", builderMethodName = "createReplyBuilder")
    public static Reply createReply(Article article, String content) {
        return of()
                .article(article)
                .content(content)
                .replyDepth(1)
                .deleteYn(Yn.N)
                .build();
    }

    @Builder(builderClassName = "createNestedReplyBuilder", builderMethodName = "createNestedReplyBuilder")
    public static Reply createNestedReply(Article article, String content, Long replyGroup, Integer replyDepth) {
        return of()
                .article(article)
                .content(content)
                .deleteYn(Yn.N)
                .replyDepth(replyDepth)
                .replyGroup(replyGroup)
                .build();
    }

    public void changeReply(String content) {
        this.content = content;
    }

    public void delete(){
        this.deleteYn = Yn.Y;
    }
}
