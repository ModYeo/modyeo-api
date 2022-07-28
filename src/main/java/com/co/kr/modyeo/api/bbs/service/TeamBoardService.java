package com.co.kr.modyeo.api.bbs.service;

import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamArticleRecommendRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamArticleRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamReplyRecommendRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamReplyRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamArticleDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamArticleResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamReplyDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.search.TeamArticleSearch;
import org.springframework.data.domain.Slice;

public interface TeamBoardService {
    void createTeamArticle(TeamArticleRequest teamArticleRequest);

    TeamArticleDetail getTeamArticle(Long teamArticleId);

    Slice<TeamArticleResponse> getTeamArticles(TeamArticleSearch teamArticleSearch);

    void updateTeamArticle(TeamArticleRequest teamArticleRequest);

    void deleteTeamArticle(Long teamArticleId);

    void createTeamReply(TeamReplyRequest teamReplyRequest);

    void updateTeamReply(TeamReplyRequest teamReplyRequest);

    void deleteTeamReply(Long teamReplyId);

    TeamReplyDetail getTeamReply(Long teamReplyId);

    void updateTeamArticleRecommend(TeamArticleRecommendRequest articleRecommendRequest);

    void updateTeamReplyRecommend(TeamReplyRecommendRequest replyRecommendRequest);
}
