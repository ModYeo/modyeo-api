package com.co.kr.modyeo.api.bbs.domain.entity;

import com.co.kr.modyeo.api.bbs.domain.entity.link.TeamReplyRecommend;
import com.co.kr.modyeo.common.entity.BaseEntity;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Lob
    private String content;

    @Column(name = "reply_depth")
    private Integer replyDepth;

    @Column(name = "reply_group")
    private Long replyGroup;

    @Column(name = "delete_yn")
    @Enumerated(value = EnumType.STRING)
    private Yn deleteYn;

    @OneToMany(mappedBy = "teamReply", cascade = CascadeType.ALL)
    private List<TeamReplyRecommend> teamReplyRecommendList = new ArrayList<>();

    @Builder(builderClassName = "of", builderMethodName = "of")
    public TeamReply(Long id, TeamArticle teamArticle, String content, Integer replyDepth, Long replyGroup, List<TeamReplyRecommend> teamReplyRecommendList, Yn deleteYn) {
        this.id = id;
        this.teamArticle = teamArticle;
        this.content = content;
        this.replyDepth = replyDepth;
        this.replyGroup = replyGroup;
        this.deleteYn = deleteYn;
        this.teamReplyRecommendList = teamReplyRecommendList;
    }

    @Builder(builderClassName = "createTeamReplyBuilder", builderMethodName = "createTeamReplyBuilder")
    public static TeamReply createTeamReply(TeamArticle article, String content) {
        return of()
                .teamArticle(article)
                .content(content)
                .deleteYn(Yn.N)
                .replyDepth(1)
                .build();
    }

    @Builder(builderClassName = "createNestedTeamReplyBuilder", builderMethodName = "createNestedTeamReplyBuilder")
    public static TeamReply createTeamNestedReply(TeamArticle article, String content, Long replyGroup, Integer replyDepth) {
        return of()
                .teamArticle(article)
                .content(content)
                .deleteYn(Yn.N)
                .replyGroup(replyGroup)
                .replyDepth(replyDepth)
                .build();
    }

    public void changeTeamReply(String content) {
        this.content = content;
    }

    public void delete(){
        this.deleteYn = Yn.Y;
    }
}
