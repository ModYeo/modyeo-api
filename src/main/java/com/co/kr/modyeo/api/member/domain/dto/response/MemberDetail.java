package com.co.kr.modyeo.api.member.domain.dto.response;

import com.co.kr.modyeo.api.category.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.domain.enumerate.Sex;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MemberDetail {

    private Long memberId;

    private String username;

    private Sex sex;

    private List<TeamResponse> teamResponseList;

    private List<CategoryResponse> categoryResponseList;

    @QueryProjection
    @Builder(builderClassName = "of",builderMethodName = "of")
    public MemberDetail(Long memberId, String username, Sex sex, List<TeamResponse> teamResponseList, List<CategoryResponse> categoryResponseList) {
        this.memberId = memberId;
        this.username = username;
        this.sex = sex;
        this.teamResponseList = teamResponseList;
        this.categoryResponseList = categoryResponseList;
    }

    public static MemberDetail createMemberDetail(Member member){
        return of()
                .memberId(member.getId())
                .username(member.getUsername())
                .sex(member.getSex())
                .teamResponseList(member.getTeamList()
                        .stream()
                        .filter(Objects::nonNull)
                        .map(team -> TeamResponse.of()
                                .id(team.getTeam().getId())
                                .name(team.getTeam().getName())
                                .build()).collect(Collectors.toList()))
                .categoryResponseList(member.getInterestCategoryList()
                        .stream()
                        .filter(Objects::nonNull)
                        .map(memberCategory -> CategoryResponse.of()
                                .id(memberCategory.getCategory().getId())
                                .name(memberCategory.getCategory().getName())
                                .build()).collect(Collectors.toList()))
                .build();
    }
}
