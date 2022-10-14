package com.co.kr.modyeo.api.bbs.domain.entity;

import com.co.kr.modyeo.api.bbs.domain.entity.link.TeamArticleRecommend;
import com.co.kr.modyeo.api.team.domain.entity.Team;
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
@Table(name = "TEAM_ARTICLE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamArticle extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Enumerated(value = EnumType.STRING)
    private Yn isHidden;

    @Column(name = "hit_count")
    private Long hitCount;
    
    @OneToMany(mappedBy = "teamArticle", cascade = CascadeType.ALL)
    private List<TeamReply> teamReplyList = new ArrayList<>();

    @OneToMany(mappedBy = "teamArticle", cascade = CascadeType.ALL)
    private List<TeamArticleRecommend> teamArticleRecommendList = new ArrayList<>();

    @Builder(builderClassName = "of", builderMethodName = "of")
    public TeamArticle(Long id, Team team, String title, String content, String filePath, Yn isHidden, Long hitCount, List<TeamReply> teamReplyList, List<TeamArticleRecommend> teamArticleRecommendList) {
        this.id = id;
        this.team = team;
        this.title = title;
        this.content = content;
        this.filePath = filePath;
        this.isHidden = isHidden;
        this.hitCount = hitCount;
        this.teamReplyList = teamReplyList;
        this.teamArticleRecommendList = teamArticleRecommendList;
    }

    @Builder(builderClassName = "createTeamArticleBuilder", builderMethodName = "createTeamArticleBuilder")
    public TeamArticle(Team team, String title, String content, String filePath, Yn isHidden) {
        this.team = team;
        this.title = title;
        this.content = content;
        this.filePath = filePath;
        this.isHidden = isHidden;
        this.hitCount = 0L;
    }

    @Builder(builderMethodName = "updateTeamArticleBuilder", builderClassName = "updateTeamArticleBuilder")
    public void changeTeamArticle(String title, String content, String filePath, Yn isHidden) {
        this.title = title;
        this.content = content;
        this.filePath = filePath;
        this.isHidden = isHidden;
    }

    public void plusHitCount() {
        this.hitCount++;
    }
}
