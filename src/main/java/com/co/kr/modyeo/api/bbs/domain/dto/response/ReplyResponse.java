package com.co.kr.modyeo.api.bbs.domain.dto.response;

import com.co.kr.modyeo.api.bbs.domain.entity.Reply;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReplyResponse {

    private Long replyId;

    private Long articleId;

    private String content;

    private Integer replyDepth;

    private Long replyGroup;

    private Yn deleteYn;

    private Long createdBy;

    private Member member;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;


    @Builder(builderClassName = "of", builderMethodName = "of")
    public ReplyResponse(Long replyId, Long articleId, String content, Integer replyDepth, Long replyGroup, Long createdBy, Member member,Yn deleteYn, LocalDateTime createdTime) {
        this.replyId = replyId;
        this.articleId = articleId;
        this.content = content;
        this.replyDepth = replyDepth;
        this.replyGroup = replyGroup;
        this.createdBy = createdBy;
        this.member = member;
        this.deleteYn = deleteYn;
        this.createdTime = createdTime;
    }

    public static ReplyResponse toDto(Reply reply) {
        return ReplyResponse.of()
                .replyId(reply.getId())
                .articleId(reply.getArticle().getId())
                .content(reply.getContent())
                .replyDepth(reply.getReplyDepth())
                .replyGroup(reply.getReplyGroup())
                .deleteYn(reply.getDeleteYn())
                .createdBy(reply.getCreatedBy())
                .createdTime(reply.getCreatedDate())
                .build();
    }

    @Getter
    @Setter
    public static class Member{
        private Long memberId;

        private String email;

        private String nickname;

        @Builder(builderMethodName = "of",builderClassName = "of")
        public Member(Long memberId, String email, String nickname) {
            this.memberId = memberId;
            this.email = email;
            this.nickname = nickname;
        }

        public static ReplyResponse.Member toDto(com.co.kr.modyeo.api.member.domain.entity.Member member) {
            return ReplyResponse.Member.of()
                    .memberId(member.getId())
                    .email(member.getEmail())
                    .nickname(member.getNickname())
                    .build();
        }
    }
}
