package com.co.kr.modyeo.api.bbs.service;

import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamArticleRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamReplyRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ReplyDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamReplyDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.search.TeamArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamArticleDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamArticleResponse;
import org.springframework.data.domain.Slice;

import java.util.List;

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
}
