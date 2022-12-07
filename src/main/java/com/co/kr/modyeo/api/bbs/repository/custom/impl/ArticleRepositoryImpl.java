package com.co.kr.modyeo.api.bbs.repository.custom.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.search.ArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.api.bbs.repository.custom.ArticleCustomRepository;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.co.kr.modyeo.api.bbs.domain.entity.QArticle.article;
import static com.co.kr.modyeo.api.bbs.domain.entity.link.QArticleRecommend.articleRecommend;
import static com.co.kr.modyeo.api.category.domain.entity.QCategory.category;
import static com.co.kr.modyeo.api.member.domain.entity.QMember.member;

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
                        .innerJoin(member).on(article.createdBy.eq(member.email))
                        .fetchJoin()
                        .where(articleTitleLike(articleSearch.getTitle()),
                                articleContentLike(articleSearch.getContent()),
                                categoryIdEq(articleSearch.getCategoryId()),
                                memberIdEq(articleSearch.getMemberId())));
    }

    private BooleanExpression memberIdEq(Long memberId) {
        return memberId != null && memberId > 0 ? member.id.eq(memberId) : null;
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
        return StringUtils.hasText(email) ? article.createdBy.eq(email) : null;
    }

    private BooleanExpression categoryIdEq(Long categoryId) {
        return categoryId != null ? category.id.eq(categoryId) : null;
    }

    private BooleanExpression articleContentLike(String content) {
        return StringUtils.hasText(content)  ? article.content.contains(content) : null;
    }

    private BooleanExpression articleTitleLike(String title) {
        return StringUtils.hasText(title)  ? article.title.contains(title) : null;
    }
}
