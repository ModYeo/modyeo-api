package com.co.kr.modyeo.api.bbs.repository.custom;

import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamReplyResponse;

import java.util.List;

public interface TeamReplyCustomRepository {
    List<TeamReplyResponse> findByTeamArticleId(Long teamArticleId);
}
