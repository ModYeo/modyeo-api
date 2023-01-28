package com.co.kr.modyeo.api.member.domain.dto.response;

import com.co.kr.modyeo.api.category.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.domain.enumerate.Sex;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponse {

    private Long id;

    private String nickname;
    private String email;
    private String username;
    private Sex sex;

    private LocalDate birthDay;
    private List<TeamResponse> teamResponseList = new ArrayList<>();
    private List<CategoryResponse> categoryResponses = new ArrayList<>();

    @Builder(builderClassName = "of", builderMethodName = "of")
    public MemberResponse(Long id, String nickname, String email, String username, Sex sex, LocalDate birthDay, List<TeamResponse> teamResponseList, List<CategoryResponse> categoryResponses) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.username = username;
        this.sex = sex;
        this.birthDay = birthDay;
        this.teamResponseList = teamResponseList;
        this.categoryResponses = categoryResponses;
    }

    public MemberResponse(Long id, String nickname, String email, String username, Sex sex, LocalDate birthDay) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.username = username;
        this.sex = sex;
        this.birthDay = birthDay;
    }

    public static MemberResponse toRes(Member member) {
        return MemberResponse.of()
                .id(member.getId())
                .email(member.getEmail())
                .username(member.getUsername())
                .sex(member.getSex())
                .nickname(member.getNickname())
                .birthDay(member.getBirthDay())
                .teamResponseList(member.getTeamList().stream()
                        .map(memberCrew -> TeamResponse.toDto(memberCrew.getTeam()))
                        .collect(Collectors.toList()))
                .categoryResponses(member.getInterestCategoryList().stream()
                        .map(memberCategory -> CategoryResponse.toDto(memberCategory.getCategory()))
                        .collect(Collectors.toList()))
                .build();
    }
}
