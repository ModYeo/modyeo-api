package com.co.kr.modyeo.api.team.domain.dto.response;

import com.co.kr.modyeo.api.team.domain.entity.enumerate.CrewLevel;
import com.co.kr.modyeo.api.team.domain.entity.link.Crew;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CrewResponse {

    private Long crewId;

    private Long memberId;

    private String nickname;

    private String profilePath;

    private CrewLevel crewLevel;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public CrewResponse(Long crewId, Long memberId, String nickname, CrewLevel crewLevel, String profilePath) {
        this.crewId = crewId;
        this.memberId = memberId;
        this.nickname = nickname;
        this.crewLevel = crewLevel;
        this.profilePath = profilePath;
    }

    public static CrewResponse toDto(Crew crew) {
        return of()
                .crewId(crew.getId())
                .memberId(crew.getMember().getId())
                .nickname(crew.getMember().getNickname())
                .profilePath(crew.getMember().getProfilePath())
                .crewLevel(crew.getCrewLevel())
                .build();
    }
}
