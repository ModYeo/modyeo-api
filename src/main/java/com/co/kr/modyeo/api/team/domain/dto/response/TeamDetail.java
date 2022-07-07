package com.co.kr.modyeo.api.team.domain.dto.response;

import com.co.kr.modyeo.api.category.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.api.team.domain.entity.Team;
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

    private String createdBy;

    private LocalDateTime createdTime;

    private List<CategoryResponse> categoryResponseList = new ArrayList<>();

    @Builder(builderClassName = "of",builderMethodName = "of")
    public TeamDetail(Long id, String name, String createdBy, LocalDateTime createdTime, List<CategoryResponse> categoryResponseList) {
        this.id = id;
        this.name = name;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.categoryResponseList = categoryResponseList;
    }

    public static TeamDetail toDto(Team team) {
        return of()
                .id(team.getId())
                .name(team.getName())
                .createdBy(team.getCreatedBy())
                .createdTime(team.getCreatedDate())
                .categoryResponseList(team.getCategoryList().stream().map(crewCategory -> CategoryResponse.of()
                        .id(crewCategory.getCategory().getId())
                        .name(crewCategory.getCategory().getName())
                        .build()).collect(Collectors.toList()))
                .build();
    }
}
