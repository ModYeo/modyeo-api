package com.co.kr.modyeo.api.bbs.service.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.request.ArticleRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.search.ArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.api.bbs.repository.ArticleRepository;
import com.co.kr.modyeo.api.bbs.repository.ReplyRepository;
import com.co.kr.modyeo.api.bbs.service.BoardService;
import com.co.kr.modyeo.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Override
    public Slice<ArticleResponse> getArticles(ArticleSearch articleSearch) {
        PageRequest pageRequest = PageRequest.of(articleSearch.getOffset(),articleSearch.getLimit(),articleSearch.getDirection(),articleSearch.getOrderBy());
        return articleRepository.searchArticle(articleSearch,pageRequest).map(ArticleResponse::toDto);
    }

    @Transactional
    @Override
    public Article createArticle(ArticleRequest articleRequest) {
        return articleRepository.save(ArticleRequest.createArticle(articleRequest));
    }

    @Transactional
    @Override
    public ArticleDetail getArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage("찾을 수 없는 게시글 입니다.")
                        .errorCode("NOT_FOUND_ARTICLE")
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        article.plusHitCount();
        return ArticleDetail.toDto(article);
    }
}
