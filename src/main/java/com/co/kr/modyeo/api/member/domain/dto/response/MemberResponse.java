package com.co.kr.modyeo.api.member.domain.dto.response;

import com.co.kr.modyeo.api.category.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.api.member.domain.entity.embed.Address;
import com.co.kr.modyeo.api.member.domain.enumerate.Sex;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponse {

    private Long id;
    private String email;
    private String username;
    private Address address;
    private Sex sex;
    private List<TeamResponse> teamResponseList = new ArrayList<>();
    private List<CategoryResponse> categoryResponses = new ArrayList<>();

    @Builder(builderClassName = "of", builderMethodName = "of")
    public MemberResponse(Long id, String email, String username, Address address, Sex sex, List<TeamResponse> teamResponseList, List<CategoryResponse> categoryResponses) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.address = address;
        this.sex = sex;
        this.teamResponseList = teamResponseList;
        this.categoryResponses = categoryResponses;
    }

    public static MemberResponse toRes(Member member){
        return MemberResponse.of()
                .id(member.getId())
                .email(member.getEmail())
                .username(member.getUsername())
                .address(member.getAddress())
                .sex(member.getSex())
                .teamResponseList(member.getMemberTeamList().stream()
                        .map(memberCrew -> TeamResponse.toRes(memberCrew.getTeam()))
                        .collect(Collectors.toList()))
                .categoryResponses(member.getInterestCategoryList().stream()
                        .map(memberCategory -> CategoryResponse.toRes(memberCategory.getCategory()))
                        .collect(Collectors.toList()))
                .build();
    }
}
