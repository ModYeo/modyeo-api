package com.co.kr.modyeo.api.member.service.impl;

import com.co.kr.modyeo.api.category.repository.CategoryRepository;
import com.co.kr.modyeo.api.member.domain.dto.request.MemberCategoryRequest;
import com.co.kr.modyeo.api.member.domain.dto.request.MemberProfilePathRequest;
import com.co.kr.modyeo.api.member.domain.dto.request.NicknameUpdateRequest;
import com.co.kr.modyeo.api.member.domain.dto.response.ApplicationMemberDetail;
import com.co.kr.modyeo.api.member.domain.dto.response.MemberDetail;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.domain.entity.link.MemberCategory;
import com.co.kr.modyeo.api.member.repository.MemberCategoryRepository;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.api.member.service.MemberService;
import com.co.kr.modyeo.api.team.domain.entity.ApplicationForm;
import com.co.kr.modyeo.api.team.repository.ApplicationFormRepository;
import com.co.kr.modyeo.api.team.repository.MemberTeamRepository;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final CategoryRepository categoryRepository;

    private final MemberCategoryRepository memberCategoryRepository;

    private final ApplicationFormRepository applicationFormRepository;

    private final MemberTeamRepository memberTeamRepository;

    @Override
    @Transactional
    public Long createMemberInfo(MemberCategoryRequest memberCategoryRequest) {
        Member member = memberRepository.findById(memberCategoryRequest.getMemberId()).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                        .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                        .build());

        if (!memberCategoryRequest.getCategoryIdList().isEmpty()) {
            List<MemberCategory> memberCategoryList = categoryRepository.findByCategoryIds(memberCategoryRequest.getCategoryIdList())
                    .stream().map(category -> MemberCategory.createMemberCategoryBuilder()
                            .member(member)
                            .category(category)
                            .build()).collect(Collectors.toList());

            memberCategoryRepository.saveAll(memberCategoryList);
        }

        return member.getId();
    }

    @Override
    public MemberDetail getMember(Long memberId) {
        Member member = memberRepository.getMember(memberId).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                        .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                        .build());

        return MemberDetail.createMemberDetail(member);
    }

    @Override
    public Long updateNickname(NicknameUpdateRequest nicknameUpdateRequest) {
        Member member = memberRepository.findById(nicknameUpdateRequest.getMemberId()).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                        .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                        .build());

        member.changeNickname(nicknameUpdateRequest.getNickname());

        return member.getId();
    }

    @Override
    public Long putProfilePath(MemberProfilePathRequest memberProfilePathRequest) {
        Member member = memberRepository.findById(memberProfilePathRequest.getMemberId()).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                        .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                        .build());

        member.changeProfilePath(member.getProfilePath());
        return member.getId();
    }

    @Override
    public ApplicationMemberDetail getTeamApplicationMember(Long memberId, Long teamId) {
        ApplicationMemberDetail detail = memberTeamRepository.findApplicationMemberByMemberId(memberId);
        if (detail == null) throw ApiException.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorCode("NOT_FOUND_APPLICATION_MEMBER")
                .errorMessage("신청자 정보를 찾을 수 없습니다.")
                .build();

        ApplicationForm applicationForm = applicationFormRepository.findApplicationFormByTeamId(teamId);
        if (Yn.N.equals(applicationForm.getBirthdayAgree())) detail.birthDayHide();
        if (Yn.N.equals(applicationForm.getSexAgree())) detail.sexHide();

        return detail;
    }
}
