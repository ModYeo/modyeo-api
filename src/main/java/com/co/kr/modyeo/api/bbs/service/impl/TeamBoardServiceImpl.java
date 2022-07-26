package com.co.kr.modyeo.api.bbs.service.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamArticleRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamReplyRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ReplyDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamArticleDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamArticleResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamReplyDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.search.TeamArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.entity.TeamArticle;
import com.co.kr.modyeo.api.bbs.repository.TeamArticleRepository;
import com.co.kr.modyeo.api.bbs.repository.TeamReplyRepository;
import com.co.kr.modyeo.api.bbs.service.TeamBoardService;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.repository.TeamRepository;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.TeamErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamBoardServiceImpl implements TeamBoardService {

    private final TeamArticleRepository teamArticleRepository;

    private final TeamReplyRepository teamReplyRepository;

    private final TeamRepository teamRepository;

    @Override
    @Transactional
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
        TeamArticle teamArticle = teamArticleRepository.findById(teamArticleId)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage("찾을 수 없는 게시글 입니다.")
                        .errorCode("NOT_FOUND_ARTICLE")
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        teamArticle.plusHitCount();

        return TeamArticleDetail.toDto(teamArticle);
    }

    @Override
    public Slice<TeamArticleResponse> getTeamArticles(TeamArticleSearch teamArticleSearch) {
        PageRequest pageRequest = PageRequest.of(teamArticleSearch.getOffset(),teamArticleSearch.getLimit(),teamArticleSearch.getDirection(),teamArticleSearch.getOrderBy());
        return teamArticleRepository.searchTeamArticle(teamArticleSearch,pageRequest).map(TeamArticleResponse::toDto);
    }

    @Override
    @Transactional
    public void updateTeamArticle(TeamArticleRequest teamArticleRequest) {
        TeamArticle teamArticle = teamArticleRepository.findById(teamArticleRequest.getArticleId()).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("NOT_FOUND_ARTICLE")
                        .errorMessage("찾을 수 없는 게시글 입니다.")
                        .build());

        teamArticle.updateTeamArticleBuilder()
                .content(teamArticleRequest.getContent())
                .filePath(teamArticleRequest.getFilePath())
                .isHidden(teamArticleRequest.getIsHidden())
                .title(teamArticleRequest.getTitle())
                .build();
    }

    @Override
    @Transactional
    public void deleteTeamArticle(Long teamArticleId) {
        TeamArticle teamArticle = teamArticleRepository.findById(teamArticleId).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("NOT_FOUND_ARTICLE")
                        .errorMessage("찾을 수 없는 게시글 입니다.")
                        .build());

        teamArticleRepository.delete(teamArticle);
    }

    @Override
    public void createTeamReply(TeamReplyRequest teamReplyRequest) {

    }

    @Override
    public void updateTeamReply(TeamReplyRequest teamReplyRequest) {

    }

    @Override
    public void deleteTeamReply(Long teamReplyId) {

    }

    @Override
    public TeamReplyDetail getTeamReply(Long teamReplyId) {
        return null;
    }
}
