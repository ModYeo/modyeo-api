package com.co.kr.modyeo.member.domain.dto.response;

import com.co.kr.modyeo.member.domain.entity.Crew;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CrewResponse {

    private Long id;
    private String name;
    private List<CategoryResponse> categoryResponseList = new ArrayList<>();


    @Builder(builderClassName = "of",builderMethodName = "of")
    public CrewResponse(Long id, String name,List<CategoryResponse> categoryResponses) {
        this.id = id;
        this.name = name;
        this.categoryResponseList = categoryResponses;
    }

    public static CrewResponse toRes(Crew crew) {
        return of()
                .id(crew.getId())
                .name(crew.getName())
                .categoryResponses(crew.getCategoryList().stream().map(crewCategory -> CategoryResponse.of()
                        .id(crewCategory.getCategory().getId())
                        .name(crewCategory.getCategory().getName())
                        .build()).collect(Collectors.toList()))
                .build();
    }
}
