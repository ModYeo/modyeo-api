package com.co.kr.modyeo.api.bbs.service;

import com.co.kr.modyeo.api.bbs.domain.dto.request.ArticleRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.search.ArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import org.springframework.data.domain.Slice;

public interface BoardService {
    Slice<ArticleResponse> getArticles(ArticleSearch articleSearch);

    Article createArticle(ArticleRequest articleRequest);

    ArticleDetail getArticle(Long id);
}
