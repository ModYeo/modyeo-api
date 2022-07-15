package com.co.kr.modyeo.api.bbs.service.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamArticleRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamArticleDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamArticleResponse;
import com.co.kr.modyeo.api.bbs.repository.TeamArticleRepository;
import com.co.kr.modyeo.api.bbs.repository.TeamReplyRepository;
import com.co.kr.modyeo.api.bbs.service.TeamBoardService;
import com.co.kr.modyeo.api.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamBoardServiceImpl implements TeamBoardService {

    private final TeamArticleRepository teamArticleRepository;

    private final TeamReplyRepository teamReplyRepository;

    private final TeamRepository teamRepository;

    @Override
    public void createTeamArticle(TeamArticleRequest teamArticleRequest) {

    }

    @Override
    public TeamArticleDetail getTeamArticle(Long teamArticleId) {
        return null;
    }

    @Override
    public List<TeamArticleResponse> getTeamArticles(TeamArticleSearch teamArticleSearch) {
        return null;
    }

    @Override
    public void updateTeamArticle(TeamArticleRequest teamArticleRequest) {

    }

    @Override
    public void deleteTeamArticle(Long teamArticleId) {

    }
}
