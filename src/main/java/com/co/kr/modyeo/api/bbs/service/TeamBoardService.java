package com.co.kr.modyeo.api.bbs.service;

import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamArticleRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.search.TeamArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamArticleDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamArticleResponse;

import java.util.List;

public interface TeamBoardService {
    void createTeamArticle(TeamArticleRequest teamArticleRequest);

    TeamArticleDetail getTeamArticle(Long teamArticleId);

    List<TeamArticleResponse> getTeamArticles(TeamArticleSearch teamArticleSearch);

    void updateTeamArticle(TeamArticleRequest teamArticleRequest);

    void deleteTeamArticle(Long teamArticleId);
}
