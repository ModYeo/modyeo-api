package com.co.kr.modyeo.api.team.domain.dto.response;

import com.co.kr.modyeo.api.category.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.domain.entity.enumerate.ScaleLevel;
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
public class TeamDetail {

    private Long id;

    private String name;

    private String description;

    private Long createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;

    private List<CategoryResponse> categoryResponseList = new ArrayList<>();

    private int crewCount;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public TeamDetail(Long id, String name, String description, Long createdBy, LocalDateTime createdTime, List<CategoryResponse> categoryResponseList, int crewCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.categoryResponseList = categoryResponseList;
        this.crewCount = crewCount;
    }

    public static TeamDetail toDto(Team team) {
        return of()
                .id(team.getId())
                .name(team.getName())
                .description(team.getDescription())
                .createdBy(team.getCreatedBy())
                .createdTime(team.getCreatedDate())
                .categoryResponseList(team.getCategoryList().stream().map(crewCategory -> CategoryResponse.of()
                        .id(crewCategory.getCategory().getId())
                        .name(crewCategory.getCategory().getName())
                        .build()).collect(Collectors.toList()))
                .crewCount(team.getCrewList().size())
                .build();
    }
}
