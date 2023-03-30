package com.co.kr.modyeo.api.bbs.service.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamReplyRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamArticleDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamReplyResponse;
import com.co.kr.modyeo.api.bbs.domain.entity.TeamArticle;
import com.co.kr.modyeo.api.bbs.domain.entity.TeamReply;
import com.co.kr.modyeo.api.bbs.repository.TeamArticleRecommendRepository;
import com.co.kr.modyeo.api.bbs.repository.TeamArticleRepository;
import com.co.kr.modyeo.api.bbs.repository.TeamReplyRecommendRepository;
import com.co.kr.modyeo.api.bbs.repository.TeamReplyRepository;
import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.repository.TeamRepository;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.BoardErrorCode;
import com.co.kr.modyeo.common.exception.code.MemberErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class TeamBoardServiceImplTest {

    private TeamBoardServiceImpl teamBoardService;

    @Mock
    private TeamArticleRepository teamArticleRepository;

    @Mock
    private TeamReplyRepository teamReplyRepository;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private TeamArticleRecommendRepository teamArticleRecommendRepository;

    @Mock
    private TeamReplyRecommendRepository teamReplyRecommendRepository;

    Category FIXTURE_CAT_01 = Category.of()
            .id(1L)
            .name("테스트 카테고리")
            .imagePath("String")
            .useYn(Yn.Y)
            .build();


    Team FIXTURE_TEAM_01 = Team.of()
            .id(1L)
            .name("테스트 팀")
            .categoryList(new ArrayList<>())
            .crewList(new ArrayList<>())
            .build();

    TeamArticle FIXTURE_ART_01 = TeamArticle.of()
            .id(1L)
            .title("test")
            .content("test")
            .team(FIXTURE_TEAM_01)
            .hitCount(1L)
            .teamArticleRecommendList(new ArrayList<>())
            .build();

    TeamReply FIXTURE_REPLY_01 = TeamReply.of()
            .id(1L)
            .teamArticle(FIXTURE_ART_01)
            .content("test")
            .replyDepth(0)
            .build();

    TeamReply FIXTURE_REPLY_02 = TeamReply.of()
            .id(2L)
            .teamArticle(FIXTURE_ART_01)
            .content("test")
            .replyDepth(1)
            .replyGroup(FIXTURE_REPLY_01.getId())
            .build();

    Member FIXTURE_MEM_01 = Member.of()
            .id(1L)
            .nickname("tester")
            .email("test@qweqwe.com")
            .build();

    TeamReplyResponse FIXTURE_REPLY_RES_01 = TeamReplyResponse.of()
            .replyId(1L)
            .teamArticleId(1L)
            .member(TeamReplyResponse.Member.of()
                    .memberId(1L)
                    .nickname("tester")
                    .email("test@qweqwe.com")
                    .build())
            .content("test")
            .build();

    TeamReplyRequest FIXTURE_REPLY_REQ_01 = TeamReplyRequest.of()
            .articleId(FIXTURE_ART_01.getId())
            .content("test")
            .replyDepth(0)
            .build();

    TeamReplyRequest FIXTURE_REPLY_REQ_02 = TeamReplyRequest.of()
            .articleId(FIXTURE_ART_01.getId())
            .content("test")
            .replyDepth(1)
            .replyGroup(FIXTURE_REPLY_01.getId())
            .build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        teamBoardService = new TeamBoardServiceImpl(teamArticleRepository,teamReplyRepository,teamRepository,memberRepository,teamArticleRecommendRepository,teamReplyRecommendRepository);
    }


    @Test
    @DisplayName("팀게시글 상세조회 테스트성공1")
    void getTeamArticleSuccess1(){
        TeamArticle teamArticle = TeamArticle.of()
                .id(1L)
                .title("test")
                .content("test")
                .build();

        given(teamArticleRepository.findById(any())).willReturn(Optional.ofNullable(teamArticle));
    }

    @Test
    void getArticleSuccess() {
        given(teamArticleRepository.findTeamArticle(any())).willReturn(Optional.ofNullable(FIXTURE_ART_01));
        given(memberRepository.findById(any())).willReturn(Optional.ofNullable(FIXTURE_MEM_01));
        given(teamReplyRepository.findByTeamArticleId(any())).willReturn(List.of(FIXTURE_REPLY_RES_01));
        TeamArticleDetail article = teamBoardService.getTeamArticle(1L);

        assertThat(article.getArticleId()).isEqualTo(1L);
        assertThat(article.getReplyResponses().size()).isEqualTo(1);
        assertThat(article.getReplyResponses().get(0).getMember().getNickname()).isEqualTo("tester");
        assertThat(article.getHitCount()).isEqualTo(2L);
    }

    @Test
    void getArticleNotFound(){
        given(teamArticleRepository.findTeamArticle(any())).willThrow(ApiException.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorMessage(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage())
                .errorCode(BoardErrorCode.NOT_FOUND_ARTICLE.getCode())
                .status(HttpStatus.BAD_REQUEST)
                .build());

        assertThatThrownBy(() -> {
            teamBoardService.getTeamArticle(1L);
        }).isInstanceOf(Exception.class).hasMessageContaining(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage());
    }


    @Test
    void getArticleNotFoundMember(){
        given(teamArticleRepository.findTeamArticle(any())).willReturn(Optional.ofNullable(FIXTURE_ART_01));
        given(memberRepository.findById(any())).willThrow(ApiException.builder()
                .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                .status(HttpStatus.BAD_REQUEST)
                .build());

        assertThatThrownBy(() -> {
            teamBoardService.getTeamArticle(1L);
        }).isInstanceOf(Exception.class).hasMessageContaining(MemberErrorCode.NOT_FOUND_MEMBER.getMessage());
    }

    @Test
    void createTeamReplyDepth0(){
        given(teamReplyRepository.save(any())).willReturn(FIXTURE_REPLY_01);
        given(teamArticleRepository.findById(any())).willReturn(Optional.of(FIXTURE_ART_01));

        Long teamReply = teamBoardService.createTeamReply(FIXTURE_REPLY_REQ_01);

        assertThat(teamReply).isEqualTo(1L);
        assertThat(FIXTURE_REPLY_01.getReplyDepth()).isEqualTo(0);
    }

    @Test
    void createTeamReplyDepth1(){
        given(teamReplyRepository.save(any())).willReturn(FIXTURE_REPLY_02);
        given(teamArticleRepository.findById(any())).willReturn(Optional.of(FIXTURE_ART_01));

        Long teamReply = teamBoardService.createTeamReply(FIXTURE_REPLY_REQ_02);

        assertThat(teamReply).isEqualTo(2L);
        assertThat(FIXTURE_REPLY_02.getReplyDepth()).isEqualTo(1);
    }
}