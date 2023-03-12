package com.co.kr.modyeo.api.bbs.service.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.request.*;
import com.co.kr.modyeo.api.bbs.domain.dto.response.*;
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
import com.co.kr.modyeo.common.enumerate.Yn;
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
        return articleRepository.searchArticle(articleSearch, pageRequest);
    }

    @Transactional
    @Override
    public Long createArticle(ArticleCreateRequest articleCreateRequest) {
        Category category = categoryRepository.findById(articleCreateRequest.getCategoryId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(CategoryErrorCode.NOT_FOUND_CATEGORY.getMessage())
                        .errorCode(CategoryErrorCode.NOT_FOUND_CATEGORY.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        return articleRepository.save(ArticleCreateRequest.createArticle(articleCreateRequest, category)).getId();
    }

    @Transactional
    @Override
    public ArticleDetail getArticle(Long id) {
        //게시글 조회
        Article article = articleRepository.findArticle(id);

        //게시글 조횟 수 증가
        article.plusHitCount();

        Member member = findMember(article.getCreatedBy());

        ArticleDetail articleDetail = ArticleDetail.toDto(article);
        articleDetail.setMember(ArticleDetail.Member.toDto(member));

        List<ReplyResponse> replyResponses = replyRepository.findByArticleId(id);
        articleDetail.setReplyResponses(replyResponses);

        return articleDetail;
    }

    @Override
    @Transactional
    public Long updateArticle(ArticleUpdateRequest articleUpdateRequest) {
        Article article = findArticle(articleUpdateRequest.getArticleId());

        Category category = categoryRepository.findById(articleUpdateRequest.getCategoryId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(CategoryErrorCode.NOT_FOUND_CATEGORY.getMessage())
                        .errorCode(CategoryErrorCode.NOT_FOUND_CATEGORY.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        article.updateArticleBuilder()
                .category(category)
                .title(articleUpdateRequest.getTitle())
                .content(articleUpdateRequest.getContent())
                .filePath(articleUpdateRequest.getFilePath())
                .isHidden(articleUpdateRequest.getIsHidden())
                .build();

        return article.getId();
    }

    @Override
    @Transactional
    public void deleteArticle(Long articleId) {
        Article article = findArticle(articleId);

        articleRepository.delete(article);
    }

    @Override
    @Transactional
    public Long createReply(ReplyRequest replyRequest) {
        Article article = findArticle(replyRequest.getArticleId());

        Reply reply = replyRequest.getReplyDepth() == 0 ?
                ReplyRequest.toReply(replyRequest, article) : ReplyRequest.toNestedReply(replyRequest, article);

        replyRepository.save(reply);
        article.plusReplyCount();

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
        reply.getArticle().minusReplyCount();
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
        Member member = findMember(articleRecommendRequest.getMemberId());

        Article article = findArticle(articleRecommendRequest.getArticleId());

        ArticleRecommend findArticleRecommend = articleRecommendRepository.findByMemberAndArticle(member, article);

        if (findArticleRecommend == null) {
            ArticleRecommend articleRecommend = ArticleRecommend.createArticleRecommendBuilder()
                    .member(member)
                    .article(article)
                    .build();

            articleRecommendRepository.save(articleRecommend);
            article.plusRecommendCount();
        } else {
            findArticleRecommend.changeRecommendYn(articleRecommendRequest.getRecommendYn());
            if (Yn.Y.equals(articleRecommendRequest.getRecommendYn())){
                article.plusRecommendCount();
            } else {
                article.minusRecommendCount();
            }
        }
    }

    @Override
    @Transactional
    public void updateReplyRecommend(ReplyRecommendRequest replyRecommendRequest) {
        Member member = findMember(replyRecommendRequest.getMemberId());

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
    public List<ArticleResponse> getLikeArticles(Long memberId) {
        return articleRepository.findArticleByEmailAndRecommendY(memberId).stream()
                .map(ArticleResponse::toDto)
                .collect(Collectors.toList());
    }

    private Member findMember(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                        .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());
    }

    private Article findArticle(Long articleId){
        return articleRepository.findById(articleId).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_ARTICLE.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());
    }
}
