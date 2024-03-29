package com.co.kr.modyeo.api.bbs.domain.dto.response;

import com.co.kr.modyeo.api.bbs.domain.entity.TeamArticle;
import com.co.kr.modyeo.api.bbs.domain.entity.link.TeamArticleRecommend;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TeamArticleDetail {

    private Long articleId;

    private TeamResponse team;

    private String filePath;

    private String title;

    private String content;

    private Yn isHidden;

    private Long hitCount;

    private Integer recommendCount;

    private Long createdBy;

    private Member member;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;

    private List<TeamReplyResponse> replyResponses = new ArrayList<>();

    private List<TeamArticleRecommendResponse> articleRecommends = new ArrayList<>();

    @Builder(builderClassName = "of", builderMethodName = "of")
    public TeamArticleDetail(Long articleId,
                             String title,
                             String filePath,
                             TeamResponse team,
                             String content,
                             Yn isHidden,
                             Long hitCount,
                             Integer recommendCount,
                             Long createdBy,
                             LocalDateTime createdTime,
                             List<TeamReplyResponse> replyResponses,
                             List<TeamArticleRecommendResponse> articleRecommends) {
        this.articleId = articleId;
        this.title = title;
        this.filePath = filePath;
        this.team = team;
        this.content = content;
        this.isHidden = isHidden;
        this.hitCount = hitCount;
        this.recommendCount = recommendCount;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.replyResponses = replyResponses;
        this.articleRecommends = articleRecommends;
    }

    public static TeamArticleDetail toDto(TeamArticle teamArticle) {
        return TeamArticleDetail.of()
                .articleId(teamArticle.getId())
                .title(teamArticle.getTitle())
                .team(TeamResponse.toDto(teamArticle.getTeam()))
                .filePath(teamArticle.getFilePath())
                .content(teamArticle.getContent())
                .isHidden(teamArticle.getIsHidden())
                .hitCount(teamArticle.getHitCount())
                .recommendCount(teamArticle.getRecommendCount())
                .createdBy(teamArticle.getCreatedBy())
                .createdTime(teamArticle.getCreatedDate())
                .articleRecommends(teamArticle.getTeamArticleRecommendList().stream().filter(Objects::nonNull).map(TeamArticleRecommendResponse::toDto).collect(Collectors.toList()))
                .build();
    }

    @Getter
    @Setter
    public static class Member{

        private Long memberId;

        private String email;

        private String nickname;

        @Builder(builderClassName = "of",builderMethodName = "of")
        public Member(Long memberId, String email, String nickname) {
            this.memberId = memberId;
            this.email = email;
            this.nickname = nickname;
        }

        public static Member toDto(com.co.kr.modyeo.api.member.domain.entity.Member member) {
            return Member.of()
                    .memberId(member.getId())
                    .email(member.getEmail())
                    .nickname(member.getNickname())
                    .build();
        }
    }
}
