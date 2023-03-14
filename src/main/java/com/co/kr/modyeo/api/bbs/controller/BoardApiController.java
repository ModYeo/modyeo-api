package com.co.kr.modyeo.api.bbs.controller;

import com.co.kr.modyeo.api.bbs.domain.dto.ReplyUpdateRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.*;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ReplyDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ReplyResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.search.ArticleSearch;
import com.co.kr.modyeo.api.bbs.service.BoardService;
import com.co.kr.modyeo.api.report.domain.dto.ReportResponse;
import com.co.kr.modyeo.common.result.ResponseHandler;
import com.co.kr.modyeo.common.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "02. Board 서비스", description = "Board 서비스")
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @ApiOperation(value = "게시글 상세 조회")
    @GetMapping("/article/{article_id}")
    @ApiResponse(
            responseCode = "200",
            description = "성공",
            content = @Content(
                    schema = @Schema(implementation = ArticleDetail.class)
            )
    )
    public ResponseEntity<?> getArticle(
            @PathVariable(value = "article_id") Long id) {
        ArticleDetail articleDetail = boardService.getArticle(id);
        return ResponseHandler.generate()
                .data(articleDetail)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "게시글 슬라이스 조회 API")
    @GetMapping("/article")
    @ApiResponse(
            responseCode = "200",
            description = "성공",
            content = @Content(
                    schema = @Schema(implementation = ArticleResponse.class)
            )
    )
    public ResponseEntity<?> getArticles(ArticleSearch articleSearch) {
        Slice<ArticleResponse> articleResponses = boardService.getArticles(articleSearch);
        return ResponseHandler.generate()
                .data(articleResponses)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "게시글 생성 API")
    @PostMapping("/article")
    public ResponseEntity<?> createArticle(@Valid @RequestBody ArticleCreateRequest articleCreateRequest) {
        Long articleId = boardService.createArticle(articleCreateRequest);
        return ResponseHandler.generate()
                .data(articleId)
                .status(HttpStatus.CREATED)
                .build();
    }

    @ApiOperation(value = "게시글 수정 API")
    @PatchMapping("/article")
    public ResponseEntity<?> updateArticle(@Valid @RequestBody ArticleUpdateRequest articleUpdateRequest) {
        Long articleId = boardService.updateArticle(articleUpdateRequest);
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
    public ResponseEntity<?> createReply(@Valid @RequestBody ReplyCreateRequest replyCreateRequest) {
        Long replyId = boardService.createReply(replyCreateRequest);
        return ResponseHandler.generate()
                .data(replyId)
                .status(HttpStatus.CREATED)
                .build();
    }

    @ApiOperation(value = "댓글 수정 API")
    @PatchMapping("/reply")
    public ResponseEntity<?> updateReply(@Valid @RequestBody ReplyUpdateRequest replyUpdateRequest) {
        Long replyId = boardService.updateReply(replyUpdateRequest);
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

    @ApiOperation("게시글 좋아요 API")
    @PutMapping("/article/recommend")
    public ResponseEntity<?> updateArticleRecommend(ArticleRecommendRequest articleRecommendRequest) {
        boardService.updateArticleRecommend(articleRecommendRequest);
        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation("댓글 좋아요 API")
    @PutMapping("/Reply/recommend")
    public ResponseEntity<?> updateReplyRecommend(ReplyRecommendRequest replyRecommendRequest) {
        boardService.updateReplyRecommend(replyRecommendRequest);
        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation("내가 쓴 댓글 조회 API")
    @GetMapping("/reply/my")
    public ResponseEntity<?> getReplyMy(){
        Long memberId = SecurityUtil.getCurrentMemberId();
        List<ReplyResponse> replyResponseList = boardService.getReplyMy(memberId);
        return ResponseHandler.generate()
                .data(replyResponseList)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation("좋아요한 글 조회 API")
    @GetMapping("/article/like/{memberId}")
    public ResponseEntity<?> getLikeArticles(@PathVariable Long memberId){
        List<ArticleResponse> articleResponseList = boardService.getLikeArticles(memberId);
        return ResponseHandler.generate()
                .data(articleResponseList)
                .status(HttpStatus.OK)
                .build();
    }
}
