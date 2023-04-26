package com.co.kr.modyeo.api.schedule.service.impl;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.category.repository.CategoryRepository;
import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import com.co.kr.modyeo.api.geo.repository.EmdAreaRepository;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.api.schedule.domain.dto.request.SchedulerCreateRequest;
import com.co.kr.modyeo.api.schedule.domain.entity.MemberScheduler;
import com.co.kr.modyeo.api.schedule.domain.entity.Scheduler;
import com.co.kr.modyeo.api.schedule.domain.entity.enumurate.ApplicationType;
import com.co.kr.modyeo.api.schedule.repository.MemberSchedulerRepository;
import com.co.kr.modyeo.api.schedule.repository.SchedulerRepository;
import com.co.kr.modyeo.api.schedule.service.SchedulerService;
import com.co.kr.modyeo.common.enumerate.Yn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class SchedulerServiceImplTest {

    private SchedulerService schedulerService;

    @Mock
    private SchedulerRepository schedulerRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private EmdAreaRepository emdAreaRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private MemberSchedulerRepository memberSchedulerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        schedulerService = new SchedulerServiceImpl(schedulerRepository, memberRepository, emdAreaRepository, categoryRepository, memberSchedulerRepository);
    }

    Category FIXTURE_CAT_01 = Category.of()
            .id(1L)
            .name("테스트 카테고리")
            .imagePath("String")
            .useYn(Yn.Y)
            .build();

    EmdArea FIXTURE_EMD_01 = EmdArea.of()
            .id(1L)
            .name("test area")
            .build();

    Scheduler FIXTURE_SCH_01 = Scheduler.of()
            .id(1L)
            .title("test")
            .content("test")
            .category(FIXTURE_CAT_01)
            .emdArea(FIXTURE_EMD_01)
            .meetingDate(LocalDateTime.now())
            .build();

    SchedulerCreateRequest FIXTURE_SCH_CRE_REQ_01 = SchedulerCreateRequest.of()
            .categoryId(1L)
            .emdAreaId(1L)
            .title("test")
            .content("test")
            .build();

    Member FIXTURE_MEM_01 = Member.of()
            .id(1L)
            .email("qws458@naver.com")
            .build();

    MemberScheduler FIXTURE_MEM_SCH_01 = MemberScheduler.of()
            .member(FIXTURE_MEM_01)
            .scheduler(FIXTURE_SCH_01)
            .applicationType(ApplicationType.MADE)
            .build();

    MemberScheduler FIXTURE_MEM_SCH_02 = MemberScheduler.of()
            .member(FIXTURE_MEM_01)
            .scheduler(FIXTURE_SCH_01)
            .applicationType(ApplicationType.WAIT)
            .build();

    @Test
    void createScheduler() {
        given(categoryRepository.findById(any())).willReturn(Optional.of(FIXTURE_CAT_01));
        given(emdAreaRepository.findById(any())).willReturn(Optional.of(FIXTURE_EMD_01));
        given(schedulerRepository.save(any())).willReturn(FIXTURE_SCH_01);
        given(memberRepository.findById(any())).willReturn(Optional.of(FIXTURE_MEM_01));
        given(memberSchedulerRepository.save(any())).willReturn(FIXTURE_MEM_SCH_01);


        Long scheduler = schedulerService.createScheduler(FIXTURE_SCH_CRE_REQ_01, 1L);
        assertThat(scheduler).isEqualTo(1L);
    }
}