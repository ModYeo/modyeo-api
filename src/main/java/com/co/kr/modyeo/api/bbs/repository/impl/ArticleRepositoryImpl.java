package com.co.kr.modyeo.api.bbs.repository.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.search.ArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.api.bbs.repository.custom.ArticleCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

public class ArticleRepositoryImpl extends Querydsl4RepositorySupport implements ArticleCustomRepository {

    public ArticleRepositoryImpl() {
        super(Article.class);
    }

    @Override
    public Slice<Article> searchArticle(ArticleSearch articleSearch, PageRequest pageRequest) {
        return null;
    }
}
