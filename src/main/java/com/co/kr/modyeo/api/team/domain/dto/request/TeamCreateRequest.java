package com.co.kr.modyeo.api.team.domain.dto.request;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.member.auth.domain.dto.MemberJoinDto;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TeamCreateRequest {

    @NotNull
    private String name;

    private String description;

    private String profilePath;

    @NotNull(message = "카테고리 선택은 필수 입니다.")
    private List<Long> categoryIdList = new ArrayList<>();

    @NotNull(message = "활동지역 선택은 필수 입니다.")
    private List<EmdAreaDto> emdAreaList = new ArrayList<>();

    @Builder
    public TeamCreateRequest( String name, String description, String profilePath, List<Long> categoryIdList) {
        this.name = name;
        this.description = description;
        this.profilePath = profilePath;
        this.categoryIdList = categoryIdList;
    }

    public static Team toEntity(TeamCreateRequest teamCreateRequest){
        return Team.createTeamBuilder()
                .name(teamCreateRequest.name)
                .profilePath(teamCreateRequest.getProfilePath())
                .description(teamCreateRequest.getDescription())
                .build();
    }

    public EmdAreaDto getEmdAreaDto(Long emdAreaId) {
        return this.emdAreaList.stream()
                .filter(emdAreaDto -> emdAreaDto.getId().equals(emdAreaId))
                .findFirst()
                .orElseThrow();
    }

    @Getter
    @Setter
    public static class EmdAreaDto {

        private Long id;

        private int limitMeters;

        public static List<Long> getIdList(List<TeamCreateRequest.EmdAreaDto> emdAreaDtoList) {
            return emdAreaDtoList.stream()
                    .map(TeamCreateRequest.EmdAreaDto::getId)
                    .collect(Collectors.toList());
        }
    }
}
