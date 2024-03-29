package com.co.kr.modyeo.api.bbs.repository.custom;

import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.search.ArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface ArticleCustomRepository {
    Slice<ArticleResponse> searchArticle(ArticleSearch articleSearch, PageRequest pageRequest);

    List<Article> findArticleByEmail(Long memberId);

    List<Article> findArticleByEmailAndRecommendY(Long memberId);

    Optional<Article> findArticle(Long id);
}
