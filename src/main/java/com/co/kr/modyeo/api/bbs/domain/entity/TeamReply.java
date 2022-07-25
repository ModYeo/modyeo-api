package com.co.kr.modyeo.api.bbs.domain.entity;

import com.co.kr.modyeo.api.bbs.domain.entity.link.TeamArticleRecommend;
import com.co.kr.modyeo.api.bbs.domain.entity.link.TeamReplyRecommend;
import com.co.kr.modyeo.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "TEAM_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamReply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_reply_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_article_id")
    private TeamArticle teamArticle;

    private String content;

    @Column(name = "reply_depth")
    private Integer replyDepth;

    @Column(name = "reply_group")
    private Long replyGroup;

    @OneToMany(mappedBy = "teamReply",cascade = CascadeType.ALL)
    private List<TeamReplyRecommend> teamReplyRecommendList = new ArrayList<>();
}
