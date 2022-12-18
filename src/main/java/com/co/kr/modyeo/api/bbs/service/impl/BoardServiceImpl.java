package com.co.kr.modyeo.api.bbs.service.impl;

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
import com.co.kr.modyeo.api.bbs.domain.entity.link.ArticleRecommend;
import com.co.kr.modyeo.api.bbs.domain.entity.link.ReplyRecommend;
import com.co.kr.modyeo.api.bbs.repository.ArticleRecommendRepository;
import com.co.kr.modyeo.api.bbs.repository.ArticleRepository;
import com.co.kr.modyeo.api.bbs.repository.ReplyRecommendRepository;
import com.co.kr.modyeo.api.bbs.repository.ReplyRepository;
import com.co.kr.modyeo.api.bbs.service.BoardService;
import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.category.repository.CategoryRepository;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.BoardErrorCode;
import com.co.kr.modyeo.common.exception.code.CategoryErrorCode;
import com.co.kr.modyeo.common.exception.code.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final ArticleRepository articleRepository;

    private final ReplyRepository replyRepository;

    private final CategoryRepository categoryRepository;

    private final MemberRepository memberRepository;

    private final ArticleRecommendRepository articleRecommendRepository;

    private final ReplyRecommendRepository replyRecommendRepository;

    @Override
    public Slice<ArticleResponse> getArticles(ArticleSearch articleSearch) {
        PageRequest pageRequest = PageRequest.of(articleSearch.getOffset(), articleSearch.getLimit(), articleSearch.getDirection(), articleSearch.getOrderBy());
        return articleRepository.searchArticle(articleSearch, pageRequest).map(ArticleResponse::toDto);
    }

    @Transactional
    @Override
    public Long createArticle(ArticleRequest articleRequest) {
        Category category = categoryRepository.findById(articleRequest.getCategoryId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(CategoryErrorCode.NOT_FOUND_CATEGORY.getMessage())
                        .errorCode(CategoryErrorCode.NOT_FOUND_CATEGORY.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        return articleRepository.save(ArticleRequest.createArticle(articleRequest, category)).getId();
    }

    @Transactional
    @Override
    public ArticleDetail getArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_ARTICLE.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        Member member = memberRepository.findById(article.getCreatedBy()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                        .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        ArticleDetail articleDetail = ArticleDetail.toDto(article);
        articleDetail.setMember(ArticleDetail.Member.toDto(member));
        article.plusHitCount();

        return articleDetail;
    }

    @Override
    @Transactional
    public Long updateArticle(ArticleRequest articleRequest) {
        Article article = articleRepository.findById(articleRequest.getArticleId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_ARTICLE.getCode())
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

        return article.getId();
    }

    @Override
    @Transactional
    public void deleteArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_ARTICLE.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        articleRepository.delete(article);
    }

    @Override
    @Transactional
    public Long createReply(ReplyRequest replyRequest) {
        Article article = articleRepository.findById(replyRequest.getArticleId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_ARTICLE.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        Reply reply = replyRequest.getReplyDepth() == 0 ?
                ReplyRequest.toReply(replyRequest, article) : ReplyRequest.toNestedReply(replyRequest, article);

        replyRepository.save(reply);
        return reply.getId();
    }

    @Override
    @Transactional
    public Long updateReply(ReplyRequest replyRequest) {
        Reply reply = replyRepository.findById(replyRequest.getId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(BoardErrorCode.NOT_FOUND_REPLY.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_REPLY.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        reply.changeReplyBuilder()
                .content(reply.getContent())
                .build();

        return reply.getId();
    }

    @Override
    public void deleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(BoardErrorCode.NOT_FOUND_REPLY.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_REPLY.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        replyRepository.delete(reply);
    }

    @Override
    public ReplyDetail getReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(BoardErrorCode.NOT_FOUND_REPLY.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_REPLY.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        List<Reply> nestedReplies = replyRepository.findByReplyGroup(replyId);
        return ReplyDetail.toDto(reply, nestedReplies);
    }

    @Override
    @Transactional
    public void updateArticleRecommend(ArticleRecommendRequest articleRecommendRequest) {
        Member member = memberRepository.findById(articleRecommendRequest.getMemberId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                        .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        Article article = articleRepository.findById(articleRecommendRequest.getArticleId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(BoardErrorCode.NOT_FOUND_REPLY.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_REPLY.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        ArticleRecommend findArticleRecommend = articleRecommendRepository.findByMemberAndArticle(member, article);

        if (findArticleRecommend == null) {
            ArticleRecommend articleRecommend = ArticleRecommend.createArticleRecommendBuilder()
                    .member(member)
                    .article(article)
                    .build();

            articleRecommendRepository.save(articleRecommend);
        } else {
            findArticleRecommend.changeRecommendYn(articleRecommendRequest.getRecommendYn());
        }
    }

    @Override
    @Transactional
    public void updateReplyRecommend(ReplyRecommendRequest replyRecommendRequest) {
        Member member = memberRepository.findById(replyRecommendRequest.getMemberId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                        .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        Reply reply = replyRepository.findById(replyRecommendRequest.getReplyId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(BoardErrorCode.NOT_FOUND_REPLY.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_REPLY.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        ReplyRecommend findReplyRecommend = replyRecommendRepository.findByMemberAndReply(member, reply);

        if (findReplyRecommend == null) {
            ReplyRecommend replyRecommend = ReplyRecommend.createReplyRecommendBuilder()
                    .reply(reply)
                    .member(member)
                    .build();

            replyRecommendRepository.save(replyRecommend);
        } else {
            findReplyRecommend.changeRecommend(replyRecommendRequest.getRecommendYn());
        }
    }

    @Override
    public List<ReplyResponse> getReplyMy(Long memberId) {
        return replyRepository.findReplyByEmail(memberId).stream()
                .map(ReplyResponse::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> getArticleMyLike(Long memberId) {
        return articleRepository.findArticleByEmailAndRecommendY(memberId).stream()
                .map(ArticleResponse::toDto)
                .collect(Collectors.toList());
    }
}
