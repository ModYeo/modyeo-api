package com.co.kr.modyeo.api.bbs.repository.custom.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.search.ArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.api.bbs.repository.custom.ArticleCustomRepository;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.List;

import static com.co.kr.modyeo.api.bbs.domain.entity.QArticle.article;
import static com.co.kr.modyeo.api.bbs.domain.entity.link.QArticleRecommend.articleRecommend;
import static com.co.kr.modyeo.api.category.domain.entity.QCategory.category;

public class ArticleRepositoryImpl extends Querydsl4RepositorySupport implements ArticleCustomRepository {

    public ArticleRepositoryImpl() {
        super(Article.class);
    }

    @Override
    public Slice<Article> searchArticle(ArticleSearch articleSearch, PageRequest pageRequest) {
        return applySlicing(pageRequest, contentQuery ->
                contentQuery.select(article)
                        .from(article)
                        .innerJoin(article.category, category)
                        .fetchJoin()
                        .where(articleTitleLike(articleSearch.getTitle()),
                                articleContentLike(articleSearch.getContent()),
                                categoryIdEq(articleSearch.getCategoryId())));
    }

    @Override
    public List<Article> findArticleByEmail(String email) {
        return selectFrom(article)
                .where(createdByEq(email))
                .fetch();
    }

    @Override
    public List<Article> findArticleByEmailAndRecommendY(String email) {
        return select(article)
                .from(articleRecommend)
                .innerJoin(articleRecommend.article, article)
                .where(createdByEq(email),
                        articleRecommend.recommendYn.eq(Yn.Y))
                .fetch();
    }

    private BooleanExpression createdByEq(String email){
        return email != null && !email.equals("") ? article.createdBy.eq(email) : null;
    }

    private BooleanExpression categoryIdEq(Long categoryId) {
        return categoryId != null ? category.id.eq(categoryId) : null;
    }

    private BooleanExpression articleContentLike(String content) {
        return content != null ? article.content.contains(content) : null;
    }

    private BooleanExpression articleTitleLike(String title) {
        return title != null ? article.title.contains(title) : null;
    }
}
