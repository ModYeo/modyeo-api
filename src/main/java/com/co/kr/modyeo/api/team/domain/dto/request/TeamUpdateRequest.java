package com.co.kr.modyeo.api.team.domain.dto.request;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TeamUpdateRequest {

    @NotNull
    private Long teamId;

    @NotNull
    private String name;

    private String description;

    private String profilePath;

    private List<TeamUpdateRequest.CategoryDto> categoryDtoList = new ArrayList<>();

    public TeamUpdateRequest(Long teamId, String name, String description, String profilePath, List<CategoryDto> categoryDtoList) {
        this.teamId = teamId;
        this.name = name;
        this.description = description;
        this.profilePath = profilePath;
        this.categoryDtoList = categoryDtoList;
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
