package com.co.kr.modyeo.api.member.domain.dto.response;

import com.co.kr.modyeo.api.category.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.domain.enumerate.Sex;
import com.co.kr.modyeo.api.team.domain.dto.response.MemberTeamResponse;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import com.co.kr.modyeo.api.team.domain.entity.link.MemberTeam;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MemberDetail {

    private Long memberId;

    private String username;

    private String nickname;

    private String profilePath;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate birthDay;

    private Sex sex;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;

    private List<TeamResponse> teamResponseList;

    private List<CategoryResponse> categoryResponseList;

    private List<MemberCollectionInfoResponse> collectionInfoResponseList;

    private List<MemberActiveAreaResponse> memberActiveAreaResponseList;

    private List<MemberTeamResponse> applicationTeamList;

    @QueryProjection
    @Builder(builderClassName = "of", builderMethodName = "of")
    public MemberDetail(Long memberId,
                        String username,
                        Sex sex,
                        String nickname,
                        String profilePath,
                        LocalDate birthDay,
                        List<TeamResponse> teamResponseList,
                        List<CategoryResponse> categoryResponseList,
                        LocalDateTime createdTime,
                        List<MemberCollectionInfoResponse> collectionInfoResponseList,
                        List<MemberActiveAreaResponse> memberActiveAreaResponseList,
                        List<MemberTeamResponse> applicationTeamList) {
        this.memberId = memberId;
        this.username = username;
        this.sex = sex;
        this.nickname = nickname;
        this.profilePath = profilePath;
        this.birthDay = birthDay;
        this.createdTime = createdTime;
        this.teamResponseList = teamResponseList;
        this.categoryResponseList = categoryResponseList;
        this.collectionInfoResponseList = collectionInfoResponseList;
        this.memberActiveAreaResponseList = memberActiveAreaResponseList;
        this.applicationTeamList = applicationTeamList;
    }

    public static MemberDetail createMemberDetail(Member member, List<MemberTeam> memberTeamList) {
        return of()
                .memberId(member.getId())
                .username(member.getUsername())
                .sex(member.getSex())
                .nickname(member.getNickname())
                .profilePath(member.getProfilePath())
                .birthDay(member.getBirthDay())
                .createdTime(member.getCreatedDate())
                .teamResponseList(member.getTeamList()
                        .stream()
                        .filter(Objects::nonNull)
                        .map(team -> TeamResponse.of()
                                .id(team.getTeam().getId())
                                .name(team.getTeam().getName())
                                .description(team.getTeam().getDescription())
                                .categoryResponses(team.getTeam().getCategoryList()
                                        .stream().map(teamCategory -> CategoryResponse.of()
                                                .id(teamCategory.getCategory().getId())
                                                .name(teamCategory.getCategory().getName())
                                                .build())
                                        .collect(Collectors.toList()))
                                .build()).collect(Collectors.toList()))
                .categoryResponseList(member.getInterestCategoryList()
                        .stream()
                        .filter(Objects::nonNull)
                        .map(memberCategory -> CategoryResponse.of()
                                .id(memberCategory.getCategory().getId())
                                .name(memberCategory.getCategory().getName())
                                .build()).collect(Collectors.toList()))
                .collectionInfoResponseList(member.getMemberCollectionInfoList()
                        .stream()
                        .filter(Objects::nonNull)
                        .map(memberCollectionInfo -> MemberCollectionInfoResponse.of()
                                .collectionInfoId(memberCollectionInfo.getCollectionInfo().getId())
                                .collectionInfoName(memberCollectionInfo.getCollectionInfo().getName())
                                .description(memberCollectionInfo.getCollectionInfo().getDescription())
                                .agreeYn(memberCollectionInfo.getAgreeYn())
                                .build())
                        .collect(Collectors.toList()))
                .memberActiveAreaResponseList(member.getMemberActiveAreaList().stream()
                        .map(MemberActiveAreaResponse::toDto)
                        .collect(Collectors.toList()))
                .applicationTeamList(memberTeamList.stream().map(MemberTeamResponse::toDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
