package com.co.kr.modyeo.api.bbs.controller;

import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamArticleRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamReplyRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ReplyDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamArticleDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamArticleResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamReplyDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.search.TeamArticleSearch;
import com.co.kr.modyeo.api.bbs.service.TeamBoardService;
import com.co.kr.modyeo.common.result.JsonResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/team-board")
@RequiredArgsConstructor
public class TeamBoardApiController {

    private final TeamBoardService teamBoardService;

    @ApiOperation(value = "팀 게시글 조회 API")
    @GetMapping("/article/{article_id}")
    public ResponseEntity<?> getArticle(
            @PathVariable(value = "article_id")Long id
    ){
        TeamArticleDetail articleDetail = teamBoardService.getTeamArticle(id);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(articleDetail)
                .build());
    }

    @ApiOperation(value = "팀 게시글 슬라이스 조회 API")
    @GetMapping("/article")
    public ResponseEntity<?> getArticles(TeamArticleSearch teamArticleSearch){
        Slice<TeamArticleResponse> teamArticles = teamBoardService.getTeamArticles(teamArticleSearch);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(teamArticles)
                .build());
    }

    @ApiOperation(value = "팀 게시글 생성 API")
    @PostMapping("/article")
    public ResponseEntity<?> createArticle(@RequestBody TeamArticleRequest articleRequest){
        teamBoardService.createTeamArticle(articleRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JsonResultData.successResultBuilder()
                        .data(null)
                        .build());
    }

    @ApiOperation(value = "팀 게시글 수정 API")
    @PatchMapping("/article")
    public ResponseEntity<?> updateArticle(@RequestBody TeamArticleRequest articleRequest){
        teamBoardService.updateTeamArticle(articleRequest);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }

    @ApiOperation(value = "팀 게시글 삭제 API")
    @DeleteMapping("/article/{article_id}")
    public ResponseEntity<?> deleteArticle(@PathVariable(value = "article_id")Long articleId){
        teamBoardService.deleteTeamArticle(articleId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }

    @ApiOperation(value = "댓글 생성 API")
    @PostMapping("/reply")
    public ResponseEntity<?> createReply(@RequestBody TeamReplyRequest teamReplyRequest){
        teamBoardService.createTeamReply(teamReplyRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JsonResultData.successResultBuilder()
                        .data(null)
                        .build());
    }

    @ApiOperation(value = "댓글 수정 API")
    @PatchMapping("/reply")
    public ResponseEntity<?> updateReply(@RequestBody TeamReplyRequest teamReplyRequest){
        teamBoardService.updateTeamReply(teamReplyRequest);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }

    @ApiOperation(value = "댓글 삭제 API")
    @DeleteMapping("/reply/{team_reply_id}")
    public ResponseEntity<?> deleteReply(
            @PathVariable(value = "team_reply_id")Long teamReplyId){
        teamBoardService.deleteTeamReply(teamReplyId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }

    @ApiOperation(value = "댓글 상세 조회 API")
    @GetMapping("/reply/{team_reply_id}")
    public ResponseEntity<?> getReply(
            @PathVariable(value = "team_reply_id")Long teamReplyId){
        TeamReplyDetail teamReplyDetail = teamBoardService.getTeamReply(teamReplyId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(teamReplyDetail)
                .build());
    }
}
