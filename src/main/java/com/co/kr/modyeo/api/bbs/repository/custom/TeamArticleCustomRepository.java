package com.co.kr.modyeo.api.bbs.repository.custom;

import com.co.kr.modyeo.api.bbs.domain.dto.response.ReplyResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamArticleResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamReplyResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.search.TeamArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.entity.TeamArticle;
import com.co.kr.modyeo.api.bbs.domain.entity.TeamReply;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface TeamArticleCustomRepository {
    Slice<TeamArticleResponse> searchTeamArticle(TeamArticleSearch teamArticleSearch, PageRequest pageRequest);

    List<TeamArticle> findArticleByMemberId(Long memberId);

    List<TeamReply> findReplyByMemberId(Long memberId);

    List<TeamArticle> findArticleByEmailAndRecommendY(Long memberId);

    Optional<TeamArticle> findTeamArticle(Long teamArticleId);

    List<TeamReplyResponse> findByArticleId(Long teamArticleId);
}
