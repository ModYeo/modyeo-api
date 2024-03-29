package com.co.kr.modyeo.api.bbs.repository.custom.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.search.ArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.api.bbs.repository.custom.ArticleCustomRepository;
import com.co.kr.modyeo.api.category.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static com.co.kr.modyeo.api.bbs.domain.entity.QArticle.article;
import static com.co.kr.modyeo.api.bbs.domain.entity.QReply.reply;
import static com.co.kr.modyeo.api.bbs.domain.entity.link.QArticleRecommend.articleRecommend;
import static com.co.kr.modyeo.api.category.domain.entity.QCategory.category;
import static com.co.kr.modyeo.api.member.domain.entity.QMember.member;
import static com.querydsl.core.types.ExpressionUtils.count;

public class ArticleRepositoryImpl extends Querydsl4RepositorySupport implements ArticleCustomRepository {

    public ArticleRepositoryImpl() {
        super(Article.class);
    }

    @Override
    public Slice<ArticleResponse> searchArticle(ArticleSearch articleSearch, PageRequest pageRequest) {
        return applySlicing(pageRequest, contentQuery ->
                contentQuery.select(Projections.constructor(ArticleResponse.class,
                                article.id,
                                article.title,
                                article.content,
                                category.id,
                                category.name,
                                article.filePath,
                                article.isHidden,
                                article.replyCount,
                                article.recommendCount,
                                article.hitCount,
                                article.createdBy,
                                member.nickname,
                                article.createdDate))
                        .from(article)
                        .innerJoin(article.category, category)
                        .innerJoin(member).on(member.id.eq(article.createdBy))
                        .where(articleTitleLike(articleSearch.getTitle()),
                                articleContentLike(articleSearch.getContent()),
                                categoryIdEq(articleSearch.getCategoryId()),
                                memberIdEq(articleSearch.getMemberId())));
    }

    private BooleanExpression memberIdEq(Long memberId) {
        return memberId != null && memberId > 0 ? member.id.eq(memberId) : null;
    }

    @Override
    public List<Article> findArticleByEmail(Long memberId) {
        return selectFrom(article)
                .where(createdByEq(memberId))
                .fetch();
    }

    @Override
    public List<Article> findArticleByEmailAndRecommendY(Long memberId) {
        return select(article)
                .from(articleRecommend)
                .innerJoin(articleRecommend.article, article)
                .where(createdByEq(memberId),
                        articleRecommend.recommendYn.eq(Yn.Y))
                .fetch();
    }

    @Override
    public Optional<Article> findArticle(Long id) {
        return Optional.ofNullable(selectFrom(article)
                .innerJoin(article.category, category)
                .fetchJoin()
                .where(articleIdEq(id))
                .fetchOne());
    }

    private BooleanExpression articleIdEq(Long articleId) {
        return articleId != null && articleId > 0 ? article.id.eq(articleId) : null;
    }

    private BooleanExpression createdByEq(Long memberId) {
        return memberId != null && memberId > 0 ? article.createdBy.eq(memberId) : null;
    }

    private BooleanExpression categoryIdEq(Long categoryId) {
        return categoryId != null ? category.id.eq(categoryId) : null;
    }

    private BooleanExpression articleContentLike(String content) {
        return StringUtils.hasText(content) ? article.content.contains(content) : null;
    }

    private BooleanExpression articleTitleLike(String title) {
        return StringUtils.hasText(title) ? article.title.contains(title) : null;
    }
}
