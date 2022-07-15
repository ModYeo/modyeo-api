package com.co.kr.modyeo.api.bbs.domain.entity;

import com.co.kr.modyeo.api.team.domain.entity.Team;
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
@Table(name = "TEAM_ARTICLE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamArticle {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_article_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    private String title;

    private String content;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "is_hidden")
    private Yn isHidden;

    @Column(name = "hit_count")
    private Long hitCount;

    @Column(name = "recommend_count")
    private Long recommendCount;

    @OneToMany(mappedBy = "teamArticle",cascade = CascadeType.ALL)
    private List<TeamReply> teamReplyList = new ArrayList<>();

    @Builder(builderClassName = "of",builderMethodName = "of")
    public TeamArticle(Long id, Team team, String title, String content, String filePath, Yn isHidden, Long hitCount, Long recommendCount, List<TeamReply> teamReplyList) {
        this.id = id;
        this.team = team;
        this.title = title;
        this.content = content;
        this.filePath = filePath;
        this.isHidden = isHidden;
        this.hitCount = hitCount;
        this.recommendCount = recommendCount;
        this.teamReplyList = teamReplyList;
    }

    @Builder(builderClassName = "createTeamArticleBuilder",builderMethodName = "createTeamArticleBuilder")
    public TeamArticle(Team team, String title, String content, String filePath, Yn isHidden) {
        this.team = team;
        this.title = title;
        this.content = content;
        this.filePath = filePath;
        this.isHidden = isHidden;
        this.hitCount = 0L;
        this.recommendCount = 0L;
    }
}
