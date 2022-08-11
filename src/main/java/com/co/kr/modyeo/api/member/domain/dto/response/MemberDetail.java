package com.co.kr.modyeo.api.member.domain.dto.response;

import com.co.kr.modyeo.api.category.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.api.member.collection.domain.dto.response.CollectionInfoResponse;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.domain.enumerate.Sex;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Integer birthYear;

    private Integer birthMonth;

    private Integer birthDay;

    private Sex sex;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;

    private List<TeamResponse> teamResponseList;

    private List<CategoryResponse> categoryResponseList;

    private List<MemberCollectionInfoResponse> collectionInfoResponseList;

    @QueryProjection
    @Builder(builderClassName = "of", builderMethodName = "of")
    public MemberDetail(Long memberId, String username, Sex sex, String nickname, String profilePath, Integer birthYear, Integer birthMonth, Integer birthDay, List<TeamResponse> teamResponseList, List<CategoryResponse> categoryResponseList, LocalDateTime createdTime, List<MemberCollectionInfoResponse> collectionInfoResponseList) {
        this.memberId = memberId;
        this.username = username;
        this.sex = sex;
        this.nickname = nickname;
        this.profilePath = profilePath;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.createdTime = createdTime;
        this.teamResponseList = teamResponseList;
        this.categoryResponseList = categoryResponseList;
        this.collectionInfoResponseList = collectionInfoResponseList;
    }

    public static MemberDetail createMemberDetail(Member member) {
        return of()
                .memberId(member.getId())
                .username(member.getUsername())
                .sex(member.getSex())
                .nickname(member.getNickname())
                .profilePath(member.getProfilePath())
                .birthYear(member.getBirthYear())
                .birthMonth(member.getBirthMonth())
                .birthDay(member.getBirthDay())
                .createdTime(member.getCreatedDate())
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
                .build();
    }
}
