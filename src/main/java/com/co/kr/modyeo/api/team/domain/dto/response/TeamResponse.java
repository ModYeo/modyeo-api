package com.co.kr.modyeo.api.team.domain.dto.response;

import com.co.kr.modyeo.api.category.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.api.geo.domain.dto.response.EmdAreaResponse;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.domain.entity.enumerate.ScaleLevel;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TeamResponse {
    private Long id;
    private String name;
    private String description;
    private String profilePath;
    private List<CategoryResponse> categoryResponseList = new ArrayList<>();

    private int crewCount;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public TeamResponse(Long id, String name, String description, String profilePath,
                        List<CategoryResponse> categoryResponses, int crewCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.profilePath = profilePath;
        this.categoryResponseList = categoryResponses;
        this.crewCount = crewCount;
    }

    public TeamResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static TeamResponse toDto(Team team) {
        return of()
                .id(team.getId())
                .name(team.getName())
                .description(team.getDescription())
                .profilePath(team.getProfilePath())
                .categoryResponses(team.getCategoryList().stream().map(crewCategory -> CategoryResponse.of()
                        .id(crewCategory.getCategory().getId())
                        .name(crewCategory.getCategory().getName())
                        .build()).collect(Collectors.toList()))
                .crewCount(team.getCrewList().size())
                .build();
    }
}
