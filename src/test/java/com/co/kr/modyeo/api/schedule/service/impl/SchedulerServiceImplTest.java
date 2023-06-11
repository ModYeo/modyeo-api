package com.co.kr.modyeo.api.schedule.service.impl;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.category.repository.CategoryRepository;
import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import com.co.kr.modyeo.api.geo.domain.entity.SidoArea;
import com.co.kr.modyeo.api.geo.domain.entity.SiggArea;
import com.co.kr.modyeo.api.geo.repository.EmdAreaRepository;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.api.schedule.domain.dto.request.SchedulerCreateRequest;
import com.co.kr.modyeo.api.schedule.domain.dto.request.SchedulerSearch;
import com.co.kr.modyeo.api.schedule.domain.dto.response.SchedulerDetail;
import com.co.kr.modyeo.api.schedule.domain.dto.response.SchedulerResponse;
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
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

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

    SidoArea FIXTURE_SIDO_01 = SidoArea.of()
            .id(1L)
            .name("test area")
            .build();

    SiggArea FIXTURE_SIGG_01 = SiggArea.of()
            .id(1L)
            .name("test area")
            .sidoArea(FIXTURE_SIDO_01)
            .build();
    EmdArea FIXTURE_EMD_01 = EmdArea.of()
            .id(1L)
            .name("test area")
            .code("test")
            .version("test")
            .siggArea(FIXTURE_SIGG_01)
            .build();

    Scheduler FIXTURE_SCH_01 = Scheduler.of()
            .id(1L)
            .title("test")
            .content("test")
            .category(FIXTURE_CAT_01)
            .emdArea(FIXTURE_EMD_01)
            .meetingDate(LocalDateTime.now())
            .memberSchedulerList(new ArrayList<>())
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

    @Test
    void getScheduler(){
        given(schedulerRepository.findById(any())).willReturn(Optional.of(FIXTURE_SCH_01));

        SchedulerDetail scheduler = schedulerService.getScheduler(1L);
        assertThat(scheduler.getSchedulerId()).isEqualTo(FIXTURE_SCH_01.getId());
    }

    @Test
    void getSchedulers(){
        given(schedulerRepository.searchScheduler(any(),any())).willReturn(new SliceImpl<>(Collections.singletonList(FIXTURE_SCH_01)));

        SchedulerSearch schedulerSearch = SchedulerSearch.of()
                .categoryId(1L)
                .direction(Sort.Direction.DESC)
                .limit(10)
                .offset(1)
                .orderBy("id")
                .build();

        Slice<SchedulerResponse> schedulers = schedulerService.getSchedulers(schedulerSearch);

        assertThat(schedulers.getSize()).isEqualTo(1);
    }

    @Test
    void deleteScheduler() {
        given(schedulerRepository.findById(any())).willReturn(Optional.of(FIXTURE_SCH_01));
        given(memberSchedulerRepository.findBySchedulerIdAndMemberId(any(),any())).willReturn(FIXTURE_MEM_SCH_01);

        schedulerService.deleteScheduler(1L, 1L);

        then(schedulerRepository).should().findById(any());
        then(schedulerRepository).should().delete(any());
    }
}