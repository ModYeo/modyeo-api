package com.co.kr.modyeo.api.team.domain.dto.request;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.domain.entity.enumerate.ScaleLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TeamRequest {

    private Long team_id;

    private Long member_id;

    private String name;

    private ScaleLevel scaleLevel;

    private List<CategoryDto> categoryDtoList = new ArrayList<>();

    @Builder
    public TeamRequest(Long team_id, String name,ScaleLevel scaleLevel, List<CategoryDto> categoryDtoList) {
        this.team_id = team_id;
        this.name = name;
        this.scaleLevel = scaleLevel;
        this.categoryDtoList = categoryDtoList;
    }

    public static Team toEntity(TeamRequest teamRequest){
        return Team.createTeamBuilder()
                .name(teamRequest.name)
                .build();
    }

    @Data
    @NoArgsConstructor
    public static class CategoryDto{
        private Long id;
        private String name;

        @Builder
        public CategoryDto(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Category toEntity(){
            return Category.of()
                    .id(this.id)
                    .name(this.name)
                    .build();
        }
    }
}
