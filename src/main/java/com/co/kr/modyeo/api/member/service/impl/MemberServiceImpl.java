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

    @Override
    @Transactional
    public void createMemberInfo(MemberCategoryRequest memberCategoryRequest) {
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
    public void updateNickname(NicknameUpdateRequest nicknameUpdateRequest) {
        Member member = memberRepository.findById(nicknameUpdateRequest.getMemberId()).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                        .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                        .build());

        member.changeNickname(nicknameUpdateRequest.getNickname());
    }

    @Override
    public void putProfilePath(MemberProfilePathRequest memberProfilePathRequest) {
        Member member = memberRepository.findById(memberProfilePathRequest.getMemberId()).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                        .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                        .build());

        member.changeProfilePath(member.getProfilePath());
    }

    @Override
    public ApplicationMemberDetail getTeamApplicationMember(Long memberId, Long teamId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                        .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                        .build());

        ApplicationForm applicationForm = applicationFormRepository.findApplicationFormByMemberIdAndTeamId(member.getEmail(), teamId);

        return null;
    }
}
