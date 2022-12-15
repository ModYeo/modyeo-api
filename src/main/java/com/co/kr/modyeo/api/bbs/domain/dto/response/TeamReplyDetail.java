package com.co.kr.modyeo.api.bbs.domain.dto.response;

import com.co.kr.modyeo.api.bbs.domain.entity.TeamReply;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TeamReplyDetail {

    private Long teamReplyId;

    private Long teamArticleId;

    private String content;

    private Integer replyDepth;

    private Long replyGroup;

    private Long createdBy;

    private Long updatedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedTime;

    private List<NestedTeamReplyDetail> nestedTeamReplyDetails = new ArrayList<>();

    @Data
    static class NestedTeamReplyDetail {

        private Long teamReplyId;

        private Long teamArticleId;

        private String content;

        private Integer replyDepth;

        private Long replyGroup;

        private Long createdBy;

        private Long updatedBy;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createdTime;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime updatedTime;

        @Builder(builderClassName = "of", builderMethodName = "of")
        public NestedTeamReplyDetail(Long teamReplyId,
                                     Long teamArticleId,
                                     String content,
                                     Integer replyDepth,
                                     Long replyGroup,
                                     Long createdBy,
                                     Long updatedBy,
                                     LocalDateTime createdTime,
                                     LocalDateTime updatedTime) {
            this.teamReplyId = teamReplyId;
            this.teamArticleId = teamArticleId;
            this.content = content;
            this.replyDepth = replyDepth;
            this.replyGroup = replyGroup;
            this.createdBy = createdBy;
            this.updatedBy = updatedBy;
            this.createdTime = createdTime;
            this.updatedTime = updatedTime;
        }

        public static NestedTeamReplyDetail toDto(TeamReply teamReply) {
            return of()
                    .teamReplyId(teamReply.getId())
                    .teamArticleId(teamReply.getTeamArticle().getId())
                    .content(teamReply.getContent())
                    .replyDepth(teamReply.getReplyDepth())
                    .replyGroup(teamReply.getReplyGroup())
                    .createdBy(teamReply.getCreatedBy())
                    .updatedBy(teamReply.getUpdatedBy())
                    .createdTime(teamReply.getCreatedDate())
                    .updatedTime(teamReply.getLastModifiedDate())
                    .build();
        }
    }

    @Builder(builderClassName = "of", builderMethodName = "of")
    public TeamReplyDetail(Long teamReplyId,
                           Long teamArticleId,
                           String content,
                           Integer replyDepth,
                           Long replyGroup,
                           Long createdBy,
                           Long updatedBy,
                           LocalDateTime createdTime,
                           LocalDateTime updatedTime,
                           List<NestedTeamReplyDetail> nestedTeamReplyDetails) {
        this.teamReplyId = teamReplyId;
        this.teamArticleId = teamArticleId;
        this.content = content;
        this.replyDepth = replyDepth;
        this.replyGroup = replyGroup;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.nestedTeamReplyDetails = nestedTeamReplyDetails;
    }

    public static TeamReplyDetail toDto(TeamReply teamReply, List<TeamReply> nestedTeamReply) {
        return of()
                .teamReplyId(teamReply.getId())
                .teamArticleId(teamReply.getTeamArticle().getId())
                .content(teamReply.getContent())
                .replyDepth(teamReply.getReplyDepth())
                .replyGroup(teamReply.getReplyGroup())
                .createdBy(teamReply.getCreatedBy())
                .updatedBy(teamReply.getUpdatedBy())
                .createdTime(teamReply.getCreatedDate())
                .updatedTime(teamReply.getLastModifiedDate())
                .nestedTeamReplyDetails(nestedTeamReply.stream().map(NestedTeamReplyDetail::toDto).collect(Collectors.toList()))
                .build();
    }
}
