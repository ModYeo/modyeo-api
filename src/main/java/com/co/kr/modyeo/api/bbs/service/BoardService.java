package com.co.kr.modyeo.api.bbs.service;

import com.co.kr.modyeo.api.bbs.domain.dto.request.ArticleRecommendRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.ArticleRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.ReplyRecommendRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.ReplyRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ReplyDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ReplyResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.search.ArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.api.bbs.domain.entity.Reply;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface BoardService {
    Slice<ArticleResponse> getArticles(ArticleSearch articleSearch);

    Long createArticle(ArticleRequest articleRequest);

    ArticleDetail getArticle(Long id);

    Long updateArticle(ArticleRequest articleRequest);

    void deleteArticle(Long articleId);

    Long createReply(ReplyRequest replyRequest);

    Long updateReply(ReplyRequest replyRequest);

    void deleteReply(Long replyId);

    ReplyDetail getReply(Long replyId);

    void updateArticleRecommend(ArticleRecommendRequest articleRecommendRequest);

    void updateReplyRecommend(ReplyRecommendRequest replyRecommendRequest);

    List<ArticleResponse> getArticlesMy(String email);

    List<ReplyResponse> getReplyMy(String email);
}
