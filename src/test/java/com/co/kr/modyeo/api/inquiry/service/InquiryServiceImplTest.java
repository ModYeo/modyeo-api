package com.co.kr.modyeo.api.inquiry.service;

import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;
import com.co.kr.modyeo.api.inquiry.domain.enumerate.InquiryStatus;
import com.co.kr.modyeo.api.inquiry.repository.AnswerRepository;
import com.co.kr.modyeo.api.inquiry.repository.InquiryRepository;
import com.co.kr.modyeo.api.inquiry.service.impl.InquiryServiceImpl;
import com.co.kr.modyeo.api.member.domain.enumerate.Authority;
import com.co.kr.modyeo.api.team.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class InquiryServiceImplTest {

    private InquiryServiceImpl inquiryService;
    @Mock
    private InquiryRepository inquiryRepository;
    @Mock
    private AnswerRepository answerRepository;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        inquiryService = new InquiryServiceImpl();
    }
    @Test
    @DisplayName("문의 상세조회 테스트")
    void getInquiryDetail(){
        Inquiry inquiry = Inquiry.of()
                .id(1L)
                .title("test")
                .content("test")
                .authority(Authority.ROLE_ADMIN)
                .status(InquiryStatus.FREQUENT)
                .build();

        given(inquiryRepository.save(inquiry));
        given(inquiryRepository.findById(1L)).willReturn(Optional.of(inquiry));
    }

}
