package com.co.kr.modyeo.api.schedule.service.impl;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.category.repository.CategoryRepository;
import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import com.co.kr.modyeo.api.geo.repository.EmdAreaRepository;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.api.schedule.domain.dto.request.SchedulerCreateRequest;
import com.co.kr.modyeo.api.schedule.domain.dto.request.SchedulerSearch;
import com.co.kr.modyeo.api.schedule.domain.dto.response.SchedulerDetail;
import com.co.kr.modyeo.api.schedule.domain.dto.response.SchedulerResponse;
import com.co.kr.modyeo.api.schedule.domain.entity.MemberScheduler;
import com.co.kr.modyeo.api.schedule.domain.entity.Scheduler;
import com.co.kr.modyeo.api.schedule.repository.MemberSchedulerRepository;
import com.co.kr.modyeo.api.schedule.repository.SchedulerRepository;
import com.co.kr.modyeo.api.schedule.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        EmdArea emdArea = emdAreaRepository.findById(schedulerCreateRequest.getEmdAreaId()).orElseThrow();
        Category category = categoryRepository.findById(schedulerCreateRequest.getCategoryId()).orElseThrow();

        Scheduler scheduler = SchedulerCreateRequest.toEntity(schedulerCreateRequest, emdArea, category);
        scheduler = schedulerRepository.save(scheduler);

        Member member = memberRepository.findById(memberId).orElseThrow();
        MemberScheduler memberScheduler = MemberScheduler.madeBuilder()
                .scheduler(scheduler)
                .member(member)
                .build();

        memberSchedulerRepository.save(memberScheduler);

        return scheduler.getId();
    }

    @Override
    public List<SchedulerResponse> getSchedulers(SchedulerSearch schedulerSearch) {
        List<Scheduler> schedulers = schedulerRepository.searchScheduler(schedulerSearch);
        return null;
    }

    @Override
    public SchedulerDetail getScheduler(Long schedulerId) {
        return null;
    }
}
