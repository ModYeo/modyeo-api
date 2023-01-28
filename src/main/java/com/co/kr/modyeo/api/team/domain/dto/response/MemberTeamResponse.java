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
                .memberResponse(MemberResponse.toDto(memberTeam.getMember()))
                .introduce(memberTeam.getIntroduce())
                .joinStatus(memberTeam.getJoinStatus())
                .createdDate(memberTeam.getCreatedDate())
                .build();
    }

    @Data
    @NoArgsConstructor
    static class MemberResponse {

        private Long memberId;

        private String nickname;

        private String profilePath;

        @Builder(builderClassName = "of",builderMethodName = "of")
        public MemberResponse(Long memberId, String nickname, String profilePath) {
            this.memberId = memberId;
            this.nickname = nickname;
            this.profilePath = profilePath;
        }

        public static MemberResponse toDto(Member member) {
            return MemberTeamResponse.MemberResponse.of()
                    .memberId(member.getId())
                    .nickname(member.getProfilePath())
                    .profilePath(member.getProfilePath())
                    .build();
        }
    }
}
