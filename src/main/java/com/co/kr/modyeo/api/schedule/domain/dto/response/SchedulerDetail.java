package com.co.kr.modyeo.api.schedule.domain.dto.response;

import com.co.kr.modyeo.api.category.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.api.geo.domain.dto.response.EmdAreaDetail;
import com.co.kr.modyeo.api.schedule.domain.entity.Scheduler;
import com.co.kr.modyeo.api.schedule.domain.entity.enumurate.ApplicationType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SchedulerDetail {

    private Long schedulerId;

    private String title;

    private String content;

    private EmdAreaDetail emdArea;

    private CategoryResponse category;

    private List<MemberDto> applicantList;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public SchedulerDetail(Long schedulerId, String title, String content, EmdAreaDetail emdArea, CategoryResponse category, List<MemberDto> applicantList) {
        this.schedulerId = schedulerId;
        this.title = title;
        this.content = content;
        this.emdArea = emdArea;
        this.category = category;
        this.applicantList = applicantList;
    }

    public static SchedulerDetail toDto(Scheduler scheduler) {
        return of()
                .schedulerId(scheduler.getId())
                .title(scheduler.getTitle())
                .content(scheduler.getContent())
                .emdArea(EmdAreaDetail.toDto(scheduler.getEmdArea()))
                .category(CategoryResponse.toDto(scheduler.getCategory()))
                .applicantList(scheduler.getMemberSchedulerList().stream().map(memberScheduler -> MemberDto.of()
                        .memberId(memberScheduler.getMember().getId())
                        .email(memberScheduler.getMember().getEmail())
                        .nickname(memberScheduler.getMember().getNickname())
                        .applicationType(memberScheduler.getApplicationType())
                        .build()).collect(Collectors.toList()))
                .build();
    }

    @Getter
    static class MemberDto {
        private Long memberId;

        private String email;

        private String nickname;

        private ApplicationType applicationType;

        @Builder(builderClassName = "of", builderMethodName = "of")
        public MemberDto(Long memberId, String email, String nickname, ApplicationType applicationType) {
            this.memberId = memberId;
            this.email = email;
            this.nickname = nickname;
            this.applicationType = applicationType;
        }
    }
}
