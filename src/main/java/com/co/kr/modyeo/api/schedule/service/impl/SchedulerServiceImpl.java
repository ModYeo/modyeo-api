package com.co.kr.modyeo.api.schedule.service.impl;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.category.repository.CategoryRepository;
import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import com.co.kr.modyeo.api.geo.repository.EmdAreaRepository;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.api.schedule.domain.dto.request.*;
import com.co.kr.modyeo.api.schedule.domain.dto.response.SchedulerDetail;
import com.co.kr.modyeo.api.schedule.domain.dto.response.SchedulerResponse;
import com.co.kr.modyeo.api.schedule.domain.entity.MemberScheduler;
import com.co.kr.modyeo.api.schedule.domain.entity.Scheduler;
import com.co.kr.modyeo.api.schedule.domain.entity.enumurate.ApplicationType;
import com.co.kr.modyeo.api.schedule.repository.MemberSchedulerRepository;
import com.co.kr.modyeo.api.schedule.repository.SchedulerRepository;
import com.co.kr.modyeo.api.schedule.service.SchedulerService;
import com.co.kr.modyeo.api.schedule.service.factory.MemberSchedulerFactory;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.CategoryErrorCode;
import com.co.kr.modyeo.common.exception.code.MemberErrorCode;
import com.co.kr.modyeo.common.exception.code.SchedulerErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SchedulerServiceImpl implements SchedulerService {

    private final SchedulerRepository schedulerRepository;

    private final MemberRepository memberRepository;

    private final EmdAreaRepository emdAreaRepository;

    private final CategoryRepository categoryRepository;

    private final MemberSchedulerRepository memberSchedulerRepository;

    @Override
    @Transactional
    public Long createScheduler(SchedulerCreateRequest schedulerCreateRequest, Long memberId) {
        EmdArea emdArea = emdAreaRepository.findById(schedulerCreateRequest.getEmdAreaId())
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("")
                        .errorMessage("")
                        .build());
        Category category = categoryRepository.findById(schedulerCreateRequest.getCategoryId())
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode(CategoryErrorCode.NOT_FOUND_CATEGORY.getCode())
                        .errorMessage(CategoryErrorCode.NOT_FOUND_CATEGORY.getMessage())
                        .build());

        Scheduler scheduler = SchedulerCreateRequest.toEntity(schedulerCreateRequest, emdArea, category);
        scheduler = schedulerRepository.save(scheduler);

        Member member = findMember(memberId);
        MemberScheduler memberScheduler = MemberScheduler.madeBuilder()
                .scheduler(scheduler)
                .member(member)
                .build();

        memberSchedulerRepository.save(memberScheduler);

        return scheduler.getId();
    }

    @Override
    public Slice<SchedulerResponse> getSchedulers(SchedulerSearch schedulerSearch) {
        PageRequest pageRequest = schedulerSearch.getPageRequest();
        Slice<Scheduler> schedulers = schedulerRepository.searchScheduler(schedulerSearch, pageRequest);
        return schedulers.map(SchedulerResponse::toDto);
    }

    @Override
    public SchedulerDetail getScheduler(Long schedulerId) {
        Scheduler scheduler = findScheduler(schedulerId);
        return SchedulerDetail.toDto(scheduler);
    }

    @Override
    @Transactional
    public void deleteScheduler(Long schedulerId) {
        Scheduler scheduler = findScheduler(schedulerId);
        schedulerRepository.delete(scheduler);
    }

    @Override
    @Transactional
    public Long updateScheduler(SchedulerUpdateRequest schedulerUpdateRequest) {
        Scheduler scheduler = findScheduler(schedulerUpdateRequest.getSchedulerId());

        Category category = categoryRepository.findById(schedulerUpdateRequest.getCategoryId())
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode(CategoryErrorCode.NOT_FOUND_CATEGORY.getCode())
                        .errorMessage(CategoryErrorCode.NOT_FOUND_CATEGORY.getMessage())
                        .build());
        //scheduler update
        if (schedulerUpdateRequest.getRecruitmentCount() != null &&
                scheduler.getMemberSchedulerList()
                        .stream()
                        .filter(memberScheduler ->
                                memberScheduler.getApplicationType().equals(ApplicationType.APPROVE) || memberScheduler.getApplicationType().equals(ApplicationType.MADE)).count() < schedulerUpdateRequest.getRecruitmentCount()) {
            throw ApiException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .errorCode(SchedulerErrorCode.RECRUITMENT_COUNT_OVER.getCode())
                    .errorMessage(SchedulerErrorCode.RECRUITMENT_COUNT_OVER.getMessage())
                    .build();
        }

        scheduler.updateScheduler(
                category,
                schedulerUpdateRequest.getImagePath(),
                schedulerUpdateRequest.getTitle(),
                schedulerUpdateRequest.getContent(),
                schedulerUpdateRequest.getRecruitmentCount());

        return scheduler.getId();
    }

    @Override
    @Transactional
    public Long updateStatus(SchedulerStatusRequest schedulerStatusRequest) {
        Scheduler scheduler = findScheduler(schedulerStatusRequest.getSchedulerId());
        scheduler.updateStatus(schedulerStatusRequest.getSchedulerStatus());
        return scheduler.getId();
    }

    @Override
    @Transactional
    public Long createMemberScheduler(MemberSchedulerCreateRequest memberSchedulerCreateRequest) {
        Scheduler scheduler = findScheduler(memberSchedulerCreateRequest.getSchedulerId());
        Member member = findMember(memberSchedulerCreateRequest.getMemberId());
        MemberScheduler memberScheduler = MemberSchedulerFactory.createMemberScheduler(member, scheduler);
        memberSchedulerRepository.save(memberScheduler);
        return scheduler.getId();
    }

    private Scheduler findScheduler(Long id) {
        return schedulerRepository.findById(id)
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .errorCode(SchedulerErrorCode.NOT_FOUND_SCHEDULER.getCode())
                        .errorMessage(SchedulerErrorCode.NOT_FOUND_SCHEDULER.getMessage())
                        .build());

    }

    private Member findMember(Long id){
        return memberRepository.findById(id)
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                        .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                        .build());
    }
}
