package com.co.kr.modyeo.api.bbs.service;

import com.co.kr.modyeo.api.bbs.domain.dto.ReplyUpdateRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.*;
import com.co.kr.modyeo.api.bbs.domain.dto.response.*;
import com.co.kr.modyeo.api.bbs.domain.dto.search.ArticleSearch;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface BoardService {
    Slice<ArticleResponse> getArticles(ArticleSearch articleSearch);

    Long createArticle(ArticleCreateRequest articleCreateRequest);

    ArticleDetail getArticle(Long id);

    Long updateArticle(ArticleUpdateRequest articleUpdateRequest);

    void deleteArticle(Long articleId);

    Long createReply(ReplyCreateRequest replyCreateRequest);

    Long updateReply(ReplyUpdateRequest replyUpdateRequest);

    void deleteReply(Long replyId, Long memberId);

    ReplyDetail getReply(Long replyId);

    void updateArticleRecommend(ArticleRecommendRequest articleRecommendRequest);

    void updateReplyRecommend(ReplyRecommendRequest replyRecommendRequest);

    List<ReplyResponse> getReplyMy(Long memberId);

    List<ArticleResponse> getLikeArticles(Long memberId);
}
