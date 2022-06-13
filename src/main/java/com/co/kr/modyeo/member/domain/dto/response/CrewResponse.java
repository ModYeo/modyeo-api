package com.co.kr.modyeo.member.domain.dto.response;

import com.co.kr.modyeo.member.domain.entity.Crew;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CrewResponse {

    private Long id;
    private String name;


    @Builder(builderClassName = "of",builderMethodName = "of")
    public CrewResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CrewResponse toRes(Crew crew) {
        return CrewResponse.of()
                .id(crew.getId())
                .name(crew.getName())
                .build();
    }
}
