package com.co.kr.modyeo.api.bbs.repository.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.response.QArticleResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.search.ArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.api.bbs.repository.custom.ArticleCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import static com.co.kr.modyeo.api.bbs.domain.entity.QArticle.article;
import static com.co.kr.modyeo.api.bbs.domain.entity.QReply.reply;
import static com.co.kr.modyeo.api.category.domain.entity.QCategory.category;

public class ArticleRepositoryImpl extends Querydsl4RepositorySupport implements ArticleCustomRepository {

    public ArticleRepositoryImpl() {
        super(Article.class);
    }

    @Override
    public Slice<ArticleResponse> searchArticle(ArticleSearch articleSearch, PageRequest pageRequest) {
        return applySlicing(pageRequest,contentQuery ->
                contentQuery.select(new QArticleResponse(
                                article.id.as("articleId"),
                                article.title,
                                article.content,
                                category.name.as("categoryName"),
                                article.filePath,
                                article.isHidden,
                                reply.id.count().as("replyCount"),
                                article.hitCount,
                                article.createdBy,
                                article.createdDate))
                        .from(article)
                        .innerJoin(article.replyList,reply)
                        .innerJoin(article.category,category)
                        .where(articleTitleLike(articleSearch.getTitle()),
                                articleContentLike(articleSearch.getContent()),
                                categoryIdEq(articleSearch.getCategoryId()))
                        .groupBy(reply.id));
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
