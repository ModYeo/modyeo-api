package com.co.kr.modyeo.api.bbs.service.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.request.ArticleRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.RecommendRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.ReplyRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ReplyDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.search.ArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.api.bbs.domain.entity.Reply;
import com.co.kr.modyeo.api.bbs.repository.ArticleRepository;
import com.co.kr.modyeo.api.bbs.repository.ReplyRepository;
import com.co.kr.modyeo.api.bbs.service.BoardService;
import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.category.repository.CategoryRepository;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.CategoryErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Slice<ArticleResponse> getArticles(ArticleSearch articleSearch) {
        PageRequest pageRequest = PageRequest.of(articleSearch.getOffset(),articleSearch.getLimit(),articleSearch.getDirection(),articleSearch.getOrderBy());
        return articleRepository.searchArticle(articleSearch,pageRequest).map(ArticleResponse::toDto);
    }

    @Transactional
    @Override
    public Article createArticle(ArticleRequest articleRequest) {
        Category category = categoryRepository.findById(articleRequest.getCategoryId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(CategoryErrorCode.NOT_FOUND_CATEGORY.getMessage())
                        .errorCode(CategoryErrorCode.NOT_FOUND_CATEGORY.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        return articleRepository.save(ArticleRequest.createArticle(articleRequest,category));
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

    @Override
    public Article updateArticle(ArticleRequest articleRequest) {
        Article article = articleRepository.findById(articleRequest.getArticleId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage("찾을 수 없는 게시글 입니다.")
                        .errorCode("NOT_FOUND_ARTICLE")
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        Category category = categoryRepository.findById(articleRequest.getCategoryId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(CategoryErrorCode.NOT_FOUND_CATEGORY.getMessage())
                        .errorCode(CategoryErrorCode.NOT_FOUND_CATEGORY.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        article.updateArticleBuilder()
                .category(category)
                .title(articleRequest.getTitle())
                .content(articleRequest.getContent())
                .filePath(articleRequest.getFilePath())
                .isHidden(articleRequest.getIsHidden())
                .build();

        return article;
    }

    @Override
    public void deleteArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage("찾을 수 없는 게시글 입니다.")
                        .errorCode("NOT_FOUND_ARTICLE")
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        articleRepository.delete(article);
    }

    @Override
    public Reply createReply(ReplyRequest replyRequest) {
        Article article = articleRepository.findById(replyRequest.getArticleId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage("찾을 수 없는 게시글 입니다.")
                        .errorCode("NOT_FOUND_ARTICLE")
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        Reply reply = replyRequest.getReplyDepth() == 0 ?
                ReplyRequest.toReply(replyRequest, article) : ReplyRequest.toNestedReply(replyRequest, article);

        replyRepository.save(reply);
        return reply;
    }

    @Override
    public Reply updateReply(ReplyRequest replyRequest) {
        Reply reply = replyRepository.findById(replyRequest.getId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage("찾을 수 없는 댓글 입니다.")
                        .errorCode("NOT_FOUND_REPLY")
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        reply.changeReplyBuilder()
                .content(reply.getContent())
                .build();

        return reply;
    }

    @Override
    public void deleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage("찾을 수 없는 댓글 입니다.")
                        .errorCode("NOT_FOUND_REPLY")
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        replyRepository.delete(reply);
    }

    @Override
    public ReplyDetail getReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage("찾을 수 없는 댓글 입니다.")
                        .errorCode("NOT_FOUND_REPLY")
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        List<Reply> nestedReplies = replyRepository.findByReplyGroup(replyId);
        return ReplyDetail.toDto(reply, nestedReplies);
    }

    @Override
    public void updateArticleRecommend(RecommendRequest recommendRequest) {
        Article article = articleRepository.findById(recommendRequest.getArticleId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage("찾을 수 없는 게시글 입니다.")
                        .errorCode("NOT_FOUND_ARTICLE")
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        article.updateRecommendCount(recommendRequest.getRecommendYn());
    }
}
