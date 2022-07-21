package com.co.kr.modyeo.api.bbs.controller;

import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamArticleRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamArticleDetail;
import com.co.kr.modyeo.api.bbs.service.TeamBoardService;
import com.co.kr.modyeo.common.result.JsonResultData;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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

    @ApiOperation(value = "팀 게시글 생성 API")
    @PostMapping("/article")
    public ResponseEntity<?> createArticle(@RequestBody TeamArticleRequest articleRequest){
        teamBoardService.createTeamArticle(articleRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JsonResultData.successResultBuilder()
                        .data(null)
                        .build());
    }
}
