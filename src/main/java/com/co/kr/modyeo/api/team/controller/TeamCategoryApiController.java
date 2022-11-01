package com.co.kr.modyeo.api.team.controller;

import com.co.kr.modyeo.api.team.domain.entity.link.TeamCategory;
import com.co.kr.modyeo.api.team.service.TeamCategoryService;
import com.co.kr.modyeo.common.result.JsonResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api("팀 카테고리 api controller")
@RequestMapping("/api")
public class TeamCategoryApiController {
    private final TeamCategoryService teamCategoryService;

    @ApiOperation(value = "팀_카테고리 생성 API")
    @PostMapping("/team/{team_id}/category/{category_id}")
    public ResponseEntity<?> createTeamCategory(
            @PathVariable("team_id") Long crewId,
            @PathVariable("category_id") Long categoryId
    ){
        Long teamCategoryId = teamCategoryService.createTeamCategory(crewId, categoryId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JsonResultData.successResultBuilder()
                        .data(teamCategoryId)
                        .build());
    }

    @ApiOperation(value = "팀_카테고리 삭제 API")
    @DeleteMapping("/team_category/{team_category_id}")
    public ResponseEntity<?> deleteTeamCategory(
            @PathVariable("team_category_id") Long teamCategoryId
    ){
        teamCategoryService.deleteTeamCategory(teamCategoryId);
        return ResponseEntity.ok(JsonResultData
                .successResultBuilder()
                .data(null)
                .build());
    }

}
