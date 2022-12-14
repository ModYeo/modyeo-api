package com.co.kr.modyeo.api.bbs.repository.custom;

import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamArticleResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.search.TeamArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.entity.TeamArticle;
import com.co.kr.modyeo.api.bbs.domain.entity.TeamReply;
import com.querydsl.core.Fetchable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface TeamArticleCustomRepository {
    Slice<TeamArticle> searchTeamArticle(TeamArticleSearch teamArticleSearch, PageRequest pageRequest);

    List<TeamArticle> findArticleByEmail(String email);

    List<TeamReply> findReplyByEmail(String email);

    List<TeamArticle> findArticleByEmailAndRecommendY(String email);
}
