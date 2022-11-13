package com.co.kr.modyeo.api.bbs.controller;

import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamArticleRecommendRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamArticleRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamReplyRecommendRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamReplyRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.response.*;
import com.co.kr.modyeo.api.bbs.domain.dto.search.TeamArticleSearch;
import com.co.kr.modyeo.api.bbs.service.TeamBoardService;
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
@RequestMapping("/api/team-board")
@Api("팀 게시판 API Controller")
@RequiredArgsConstructor
public class TeamBoardApiController {

    private final TeamBoardService teamBoardService;

    @ApiOperation(value = "팀 게시글 조회 API")
    @GetMapping("/article/{article_id}")
    public ResponseEntity<?> getArticle(@PathVariable(value = "article_id") Long id) {
        TeamArticleDetail articleDetail = teamBoardService.getTeamArticle(id);
        return ResponseHandler.generate()
                .data(articleDetail)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "팀 게시글 슬라이스 조회 API")
    @GetMapping("/article")
    public ResponseEntity<?> getArticles(TeamArticleSearch teamArticleSearch) {
        Slice<TeamArticleResponse> teamArticles = teamBoardService.getTeamArticles(teamArticleSearch);
        return ResponseHandler.generate()
                .data(teamArticles)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "팀 게시글 생성 API")
    @PostMapping("/article")
    public ResponseEntity<?> createArticle(@RequestBody TeamArticleRequest articleRequest) {
        Long articleId = teamBoardService.createTeamArticle(articleRequest);
        return ResponseHandler.generate()
                .data(articleId)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "팀 게시글 수정 API")
    @PatchMapping("/article")
    public ResponseEntity<?> updateArticle(@RequestBody TeamArticleRequest articleRequest) {
        Long articleId = teamBoardService.updateTeamArticle(articleRequest);
        return ResponseHandler.generate()
                .data(articleId)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "팀 게시글 삭제 API")
    @DeleteMapping("/article/{article_id}")
    public ResponseEntity<?> deleteArticle(@PathVariable(value = "article_id") Long articleId) {
        teamBoardService.deleteTeamArticle(articleId);
        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "팀 댓글 생성 API")
    @PostMapping("/reply")
    public ResponseEntity<?> createReply(@RequestBody TeamReplyRequest teamReplyRequest) {
        Long teamReplyId = teamBoardService.createTeamReply(teamReplyRequest);
        return ResponseHandler.generate()
                .data(teamReplyId)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "팀 댓글 수정 API")
    @PatchMapping("/reply")
    public ResponseEntity<?> updateReply(@RequestBody TeamReplyRequest teamReplyRequest) {
        Long teamReplyId = teamBoardService.updateTeamReply(teamReplyRequest);
        return ResponseHandler.generate()
                .data(teamReplyId)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "팀 댓글 삭제 API")
    @DeleteMapping("/reply/{team_reply_id}")
    public ResponseEntity<?> deleteReply(
            @PathVariable(value = "team_reply_id") Long teamReplyId) {
        teamBoardService.deleteTeamReply(teamReplyId);
        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "팀 댓글 상세 조회 API")
    @GetMapping("/reply/{team_reply_id}")
    public ResponseEntity<?> getReply(
            @PathVariable(value = "team_reply_id") Long teamReplyId) {
        TeamReplyDetail teamReplyDetail = teamBoardService.getTeamReply(teamReplyId);
        return ResponseHandler.generate()
                .data(teamReplyDetail)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "팀 게시글 추천 API")
    @PutMapping("/article/recommend")
    public ResponseEntity<?> updateTeamArticleRecommend(TeamArticleRecommendRequest teamArticleRecommendRequest) {
        teamBoardService.updateTeamArticleRecommend(teamArticleRecommendRequest);
        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "팀 댓글 추천 API")
    @PutMapping("/reply/recommend")
    public ResponseEntity<?> updateTeamReplyRecommend(TeamReplyRecommendRequest teamReplyRecommendRequest) {
        teamBoardService.updateTeamReplyRecommend(teamReplyRecommendRequest);
        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "내가 쓴 게시글 조회 API")
    @GetMapping("/article/my")
    public ResponseEntity<?> getTeamArticleMy(){
        String email =  SecurityUtil.getCurrentEmail();
        List<TeamArticleResponse> articleResponseList = teamBoardService.getArticlesMy(email);
        return ResponseHandler.generate()
                .data(articleResponseList)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "내가 쓴 댓글 조회 API")
    @GetMapping("/reply/my")
    public ResponseEntity<?> getTeamReplyMy(){
        String email =  SecurityUtil.getCurrentEmail();
        List<TeamReplyResponse> teamReplyResponseList = teamBoardService.getReplyMy(email);
        return ResponseHandler.generate()
                .data(teamReplyResponseList)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation("내가 좋아요한 글 조회 API")
    @GetMapping("/article/my-like")
    public ResponseEntity<?> getTeamArticleMyLike(){
        String email = SecurityUtil.getCurrentEmail();
        List<TeamArticleResponse> articleResponseList = teamBoardService.getArticleMyLike(email);
        return ResponseHandler.generate()
                .data(articleResponseList)
                .status(HttpStatus.OK)
                .build();
    }
}
