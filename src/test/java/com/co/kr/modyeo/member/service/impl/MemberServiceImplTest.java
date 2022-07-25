package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.api.category.repository.CategoryRepository;
import com.co.kr.modyeo.api.member.domain.dto.request.NicknameUpdateRequest;
import com.co.kr.modyeo.api.member.domain.dto.response.MemberResponse;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.repository.MemberCategoryRepository;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.api.member.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class MemberServiceImplTest {

    private MemberServiceImpl memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private MemberCategoryRepository memberCategoryRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        memberService = new MemberServiceImpl(memberRepository,categoryRepository,memberCategoryRepository);
    }

    @Test
    void getMembers(){
//        List<MemberResponse> members = memberService.getMembers();
//
//        assertThat(members.size()).isEqualTo(1);
    }

    @Test
    void updateNicknameTest(){
        NicknameUpdateRequest nicknameUpdateRequest = NicknameUpdateRequest.of()
                .memberId(1L)
                .nickname("after nickname")
                .build();

        Member member = Member.of()
                .id(1L)
                .nickname("before nickname")
                .build();

        given(memberRepository.findById(any())).willReturn(Optional.of(member));

        memberService.updateNickname(nicknameUpdateRequest);

        then(memberRepository).should().findById(any());
        assertThat(member.getNickname()).isEqualTo(nicknameUpdateRequest.getNickname());
    }
}