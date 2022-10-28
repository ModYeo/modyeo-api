package com.co.kr.modyeo.api.bbs.service.impl;

import com.co.kr.modyeo.api.bbs.domain.entity.TeamArticle;
import com.co.kr.modyeo.api.bbs.repository.TeamArticleRepository;
import com.co.kr.modyeo.api.bbs.repository.TeamReplyRepository;
import com.co.kr.modyeo.api.team.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class TeamBoardServiceImplTest {

    private TeamBoardServiceImpl teamBoardService;

    @Mock
    private TeamArticleRepository teamArticleRepository;

    @Mock
    private TeamReplyRepository teamReplyRepository;

    @Mock
    private TeamRepository teamRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        //teamBoardService = new TeamBoardServiceImpl(teamArticleRepository,teamReplyRepository,teamRepository);
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
}