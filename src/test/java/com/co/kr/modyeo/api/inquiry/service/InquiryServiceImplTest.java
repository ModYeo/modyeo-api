package com.co.kr.modyeo.api.inquiry.service;

import com.co.kr.modyeo.api.inquiry.domain.dto.Response.InquiryDetail;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.InquiryResponse;
import com.co.kr.modyeo.api.inquiry.domain.dto.request.*;
import com.co.kr.modyeo.api.inquiry.domain.dto.search.InquirySearch;
import com.co.kr.modyeo.api.inquiry.domain.entity.Answer;
import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;
import com.co.kr.modyeo.api.inquiry.domain.enumerate.InquiryStatus;
import com.co.kr.modyeo.api.inquiry.repository.AnswerRepository;
import com.co.kr.modyeo.api.inquiry.repository.InquiryRepository;
import com.co.kr.modyeo.api.inquiry.service.impl.InquiryServiceImpl;
import com.co.kr.modyeo.api.member.domain.enumerate.Authority;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@Transactional
public class InquiryServiceImplTest {
    private InquiryServiceImpl inquiryService;
    @Mock
    private InquiryRepository inquiryRepository;
    @Mock
    private AnswerRepository answerRepository;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        inquiryService = new InquiryServiceImpl(inquiryRepository, answerRepository);
    }

    @Test
    @DisplayName("문의 및 답변 입력")
    public void create_inquiry_and_answer_Test() throws Exception{
        //테스트할 값 설정
        /*InquiryCreateRequest InquiryCreateRequest = InquiryCreateRequest.of()
                .title("test title").content("test content").build();*/

        Inquiry inquiry = Inquiry.of()
                .id(2L).title("test title").content("test content")
                .status(InquiryStatus.WAITING)
                .authority(Authority.ROLE_USER)
                .build();

        //설정한 값으로 진행할 테스트 설정
        given(inquiryRepository.save(any())).willReturn(inquiry);
        //Inquiry resultInquiry = inquiryService.createInquiry(InquiryCreateRequest);

        //then : 테스트가 모두 끝났을 때 기대하는 결과와 같은지 확인
        then(inquiryRepository).should().save(any());
        //assertEquals(resultInquiry.getId(),inquiry.getId());
        //assertEquals(InquiryStatus.WAITING, resultInquiry.getStatus());

        AnswerCreateRequest answerCreateRequest = AnswerCreateRequest.of()
                .content("test Answer2")
                .build();

        Answer answer = Answer.of()
        //        .id(2L).inquiry(resultInquiry).content("test Answer4")
                .authority(Authority.ROLE_USER).build();

        //given(inquiryRepository.findById(any())).willReturn(Optional.of(resultInquiry));
        given(answerRepository.save(any())).willReturn(answer);

        Answer resultAnswer = inquiryService.createAnswer(answerCreateRequest);

        assertEquals(resultAnswer.getId(), answer.getId());
        assertEquals(resultAnswer.getContent(), answer.getContent());
        //assertEquals(resultAnswer.getInquiry().getId(), resultInquiry.getId());
        //assertEquals(1, resultInquiry.getAnswerList().size());
        //(resultAnswer.getId(), resultInquiry.getAnswerList().get(0).getId());
        assertEquals(InquiryStatus.COMPLETE, resultAnswer.getInquiry().getStatus());
    }

    @Test
    @DisplayName("문의 조회 테스트")
    void get_inquiry_test(){
        //준비
        InquirySearch inquirySearch =
                InquirySearch.of().offset(0).limit(10).title("test other property").build();

        List<Inquiry> inquiryList = new ArrayList<>(Arrays.asList(
                Inquiry.of().id(1L).title("test title1").content("test content1").build(),
                Inquiry.of().id(2L).title("test title2").content("test content2").build(),
                Inquiry.of().id(3L).title("test title3").content("test content3").build()
        ));

        Answer answer1 = Answer.of().inquiry(inquiryList.get(0)).id(1L).content("test answer1").build();
        Answer answer2 = Answer.of().inquiry(inquiryList.get(0)).id(2L).content("test answer2").build();

        inquiryList.get(0).getAnswerList().add(answer1);
        inquiryList.get(0).getAnswerList().add(answer2);

        PageRequest pageRequest = PageRequest.of(0,10);

        Slice<Inquiry> inquiries = new SliceImpl<>(inquiryList, pageRequest, false);
        //given
        given(inquiryRepository.getInquiryIndexPage(any(), any())).willReturn(inquiries);
        //when
        Slice<InquiryResponse> inquiryResponse = inquiryService.getInquiryIndexPage(inquirySearch);
        //검증
        assertEquals(inquiryList.size(), inquiryResponse.getContent().size());
        //Slice 객체에 getSize() 를 때리면 Slice 의 PageSize 를 리턴한다.
    }

    @Test
    @DisplayName("문의 상세 조회")
    void get_inquiry_detail_test(){
        Inquiry inquiry = Inquiry.of().id(1L).title("test Inquiry Search").content("test Inquiry Search").build();
        Answer answer1 = Answer.of().inquiry(inquiry).id(1L).content("test answer1").build();
        Answer answer2 = Answer.of().inquiry(inquiry).id(2L).content("test answer2").build();

        inquiry.getAnswerList().add(answer1);
        inquiry.getAnswerList().add(answer2);
        //준비
        given(inquiryRepository.findById(any())).willReturn(Optional.of(inquiry));
        //실행
        InquiryDetail inquiryDetail = inquiryService.getInquiryDetail(inquiry.getId());
        //검증
        assertThat(inquiryDetail.getContent()).isEqualTo(inquiry.getContent());
    }

    @Test
    @DisplayName("문의 업데이트 테스트")
    void update_Inquiry_test(){
        Inquiry inquiry = Inquiry.of()
                .id(1L)
                .title("test inquiry")
                .content("test content")
                .build();

        InquiryUpdateRequest InquiryUpdateRequest =
                com.co.kr.modyeo.api.inquiry.domain.dto.request.InquiryUpdateRequest.of()
                .content("updated Inquiry Content")
                .title("updated Inquiry Title")
                .build();

        given(inquiryRepository.findById(any())).willReturn(Optional.of(inquiry));

        Inquiry updatedInquiry = inquiryService.updateInquiry(InquiryUpdateRequest);

        //then(inquiryRepository).should().findById(any());
        assertEquals(updatedInquiry.getTitle(), InquiryUpdateRequest.getTitle());
        assertEquals(updatedInquiry.getContent(), InquiryUpdateRequest.getContent());
    }

    @Test
    @DisplayName("문의 삭제 조회")
    void delete_inquir_test(){
        Inquiry inquiry = Inquiry.of()
                .id(1L)
                .title("test title")
                .content("test contents")
                .build();

        given(inquiryRepository.findById(any())).willReturn(Optional.of(inquiry));

        inquiryService.deleteInquiry(anyLong());
        then(inquiryRepository).should().findById(any());
        then(inquiryRepository).should().delete(any());
    }

    @Test
    @DisplayName("답변 업데이트")
    void update_answer_test(){
        Answer answer = Answer.of().id(1L).content("test answer").build();
        AnswerUpdateRequest answerUpdateRequest = AnswerUpdateRequest.of().content("updated answer").build();

        given(answerRepository.findById(any())).willReturn(Optional.of(answer));
        Answer updatedAnswer = inquiryService.updateAnswer(answerUpdateRequest);
        Optional<Answer> expectedAnswer = answerRepository.findById(any());

        assertEquals(expectedAnswer.get().getContent(), updatedAnswer.getContent());
    }

    @Test
    @DisplayName("answer cascade 삭제 테스트") //TODO: CascadeType.ALL 작동 안됨...
    void delete_cascade_test(){
        List<Inquiry> inquiryList = new ArrayList<>();

        Inquiry inquiry = Inquiry.of().id(1L).title("test inquiry ").content("test inquiry ").build();
        inquiryList.add(inquiry);

        for(long i=0;i<100;i++){
            inquiryList.get(0).getAnswerList().add(
                    Answer.of().id(i).inquiry(inquiryList.get(0)).content(i+" test").build()
            );
        }

        given(inquiryRepository.findAll()).willReturn(inquiryList);
        given(inquiryRepository.findById(anyLong())).willReturn(Optional.of(inquiry));

        List<Inquiry> inquiries = inquiryRepository.findAll();
        inquiryService.deleteInquiry(anyLong());
        System.out.println("======= Then =======");
        then(inquiryRepository).should().delete(any());
        then(answerRepository).should().delete(any());
    }
}
