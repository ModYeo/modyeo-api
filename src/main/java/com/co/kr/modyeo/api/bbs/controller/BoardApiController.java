package com.co.kr.modyeo.api.bbs.controller;

import com.co.kr.modyeo.api.bbs.domain.dto.request.ArticleRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.ArticleRecommendRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.ReplyRecommendRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.ReplyRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ReplyDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ReplyResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.search.ArticleSearch;
import com.co.kr.modyeo.api.bbs.service.BoardService;
import com.co.kr.modyeo.common.result.JsonResultData;
import com.co.kr.modyeo.common.result.ResponseHandler;
import com.co.kr.modyeo.common.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@Api("게시판 API Controller")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @ApiOperation(value = "게시글 상세 조회")
    @GetMapping("/article/{article_id}")
    public ResponseEntity<?> getArticle(
            @PathVariable(value = "article_id") Long id
    ) {
        ArticleDetail articleDetail = boardService.getArticle(id);
        return ResponseHandler.generate()
                .data(articleDetail)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "게시글 슬라이스 조회 API")
    @GetMapping("/article")
    public ResponseEntity<?> getArticles(ArticleSearch articleSearch) {
        Slice<ArticleResponse> articleResponses = boardService.getArticles(articleSearch);
        return ResponseHandler.generate()
                .data(articleResponses)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "게시글 생성 API")
    @PostMapping("/article")
    public ResponseEntity<?> createArticle(@RequestBody ArticleRequest articleRequest) {
        Long articleId = boardService.createArticle(articleRequest);
        return ResponseHandler.generate()
                .data(articleId)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "게시글 수정 API")
    @PatchMapping("/article")
    public ResponseEntity<?> updateArticle(@RequestBody ArticleRequest articleRequest) {
        Long articleId = boardService.updateArticle(articleRequest);
        return ResponseHandler.generate()
                .data(articleId)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "게시글 삭제 API")
    @DeleteMapping("/article/{article_id}")
    public ResponseEntity<?> deleteArticle(
            @PathVariable(value = "article_id") Long articleId) {
        boardService.deleteArticle(articleId);
        return ResponseHandler.generate()
                .status(HttpStatus.NO_CONTENT)
                .data(null)
                .build();
    }

    @ApiOperation(value = "댓글 생성 API")
    @PostMapping("/reply")
    public ResponseEntity<?> createReply(@RequestBody ReplyRequest replyRequest) {
        Long replyId = boardService.createReply(replyRequest);
        return ResponseHandler.generate()
                .data(replyId)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "댓글 수정 API")
    @PatchMapping("/reply")
    public ResponseEntity<?> updateReply(@RequestBody ReplyRequest replyRequest) {
        Long replyId = boardService.updateReply(replyRequest);
        return ResponseHandler.generate()
                .data(replyId)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "댓글 삭제 API")
    @DeleteMapping("/reply/{reply_id}")
    public ResponseEntity<?> deleteReply(
            @PathVariable(value = "reply_id") Long replyId) {
        boardService.deleteReply(replyId);
        return ResponseHandler.generate()
                .status(HttpStatus.NO_CONTENT)
                .data(null)
                .build();
    }

    @ApiOperation(value = "댓글 상세 조회 API")
    @GetMapping("/reply/{reply_id}")
    public ResponseEntity<?> getReply(
            @PathVariable(value = "reply_id") Long replyId) {
        ReplyDetail replyDetail = boardService.getReply(replyId);
        return ResponseHandler.generate()
                .data(replyDetail)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation("게시글 추천 업데이트 API")
    @PutMapping("/article/recommend")
    public ResponseEntity<?> updateArticleRecommend(ArticleRecommendRequest articleRecommendRequest) {
        boardService.updateArticleRecommend(articleRecommendRequest);
        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation("댓글 추천 업데이트 API")
    @PutMapping("/Reply/recommend")
    public ResponseEntity<?> updateReplyRecommend(ReplyRecommendRequest replyRecommendRequest) {
        boardService.updateReplyRecommend(replyRecommendRequest);
        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation("내가 쓴 게시글 조회 API")
    @GetMapping("/article/my")
    public ResponseEntity<?> getArticleMy(){
        String email = SecurityUtil.getCurrentEmail();
        List<ArticleResponse> articleResponseList = boardService.getArticlesMy(email);
        return ResponseHandler.generate()
                .data(articleResponseList)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation("내가 쓴 댓글 조회 API")
    @GetMapping("/reply/my")
    public ResponseEntity<?> getReplyMy(){
        String email = SecurityUtil.getCurrentEmail();
        List<ReplyResponse> replyResponseList = boardService.getReplyMy(email);
        return ResponseHandler.generate()
                .data(replyResponseList)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation("내가 좋아요한 글 조회 API")
    @GetMapping("/article/my-like")
    public ResponseEntity<?> getArticleMyLike(){
        String email = SecurityUtil.getCurrentEmail();
        List<ArticleResponse> articleResponseList = boardService.getArticleMyLike(email);
        return ResponseHandler.generate()
                .data(articleResponseList)
                .status(HttpStatus.OK)
                .build();
    }
}
