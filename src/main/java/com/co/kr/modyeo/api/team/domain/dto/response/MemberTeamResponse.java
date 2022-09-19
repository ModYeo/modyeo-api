package com.co.kr.modyeo.api.team.domain.dto.response;

import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.team.domain.entity.enumerate.JoinStatus;
import com.co.kr.modyeo.api.team.domain.entity.link.MemberTeam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MemberTeamResponse {

    private Long memberTeamId;

    private MemberResponse memberResponse;

    private String introduce;

    private JoinStatus joinStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public MemberTeamResponse(Long memberTeamId, MemberResponse memberResponse, TeamResponse team, String introduce, JoinStatus joinStatus, LocalDateTime createdDate) {
        this.memberTeamId = memberTeamId;
        this.memberResponse = memberResponse;
        this.introduce = introduce;
        this.joinStatus = joinStatus;
        this.createdDate = createdDate;
    }

    public static MemberTeamResponse toDto(MemberTeam memberTeam){
        return of()
                .memberTeamId(memberTeam.getId())
                .memberResponse(MemberResponse.toRes(memberTeam.getMember()))
                .introduce(memberTeam.getIntroduce())
                .joinStatus(memberTeam.getJoinStatus())
                .createdDate(memberTeam.getCreatedDate())
                .build();
    }

    @Data
    @NoArgsConstructor
    static class MemberResponse {

        private Long memberId;

        private String email;

        @Builder(builderClassName = "of",builderMethodName = "of")
        public MemberResponse(Long memberId, String email) {
            this.memberId = memberId;
            this.email = email;
        }

        public static MemberResponse toRes(Member member) {
            return MemberTeamResponse.MemberResponse.of()
                    .memberId(member.getId())
                    .email(member.getEmail())
                    .build();
        }
    }
}
