package com.co.kr.modyeo.api.team.domain.dto.request;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TeamCreateRequest {

    @NotNull
    private String name;

    private String description;

    private String profilePath;

    private List<CategoryDto> categoryDtoList = new ArrayList<>();

    @Builder
    public TeamCreateRequest( String name, String description, String profilePath, List<CategoryDto> categoryDtoList) {
        this.name = name;
        this.description = description;
        this.profilePath = profilePath;
        this.categoryDtoList = categoryDtoList;
    }

    public static Team toEntity(TeamCreateRequest teamCreateRequest){
        return Team.createTeamBuilder()
                .name(teamCreateRequest.name)
                .profilePath(teamCreateRequest.getProfilePath())
                .description(teamCreateRequest.getDescription())
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
