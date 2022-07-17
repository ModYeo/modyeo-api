package com.co.kr.modyeo.api.bbs.service.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamArticleRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamArticleDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamArticleResponse;
import com.co.kr.modyeo.api.bbs.domain.entity.TeamArticle;
import com.co.kr.modyeo.api.bbs.repository.TeamArticleRepository;
import com.co.kr.modyeo.api.bbs.repository.TeamReplyRepository;
import com.co.kr.modyeo.api.bbs.service.TeamBoardService;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.repository.TeamRepository;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.TeamErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        Team team = teamRepository.findById(teamArticleRequest.getTeamId())
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode(TeamErrorCode.NOT_FOUND_TEAM.getCode())
                        .errorMessage(TeamErrorCode.NOT_FOUND_TEAM.getMessage())
                        .build());

        TeamArticle article = TeamArticleRequest.createArticle(teamArticleRequest, team);
        teamArticleRepository.save(article);
    }

    @Override
    public TeamArticleDetail getTeamArticle(Long teamArticleId) {
        return TeamArticleDetail.toDto(teamArticleRepository.findById(teamArticleId)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage("찾을 수 없는 게시글 입니다.")
                        .errorCode("NOT_FOUND_ARTICLE")
                        .status(HttpStatus.BAD_REQUEST)
                        .build()));
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
