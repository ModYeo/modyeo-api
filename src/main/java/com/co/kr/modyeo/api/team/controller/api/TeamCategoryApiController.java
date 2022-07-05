package com.co.kr.modyeo.api.team.controller.api;

import com.co.kr.modyeo.api.team.domain.entity.link.TeamCategory;
import com.co.kr.modyeo.api.team.service.TeamCategoryService;
import com.co.kr.modyeo.common.result.JsonResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TeamCategoryApiController {
    private final TeamCategoryService teamCategoryService;

    @PostMapping("/team/{team_id}/category/{category_id}")
    public ResponseEntity<?> createTeamCategory(
            @PathVariable("team_id") Long crewId,
            @PathVariable("category_id") Long categoryId
    ){
        TeamCategory teamCategory = teamCategoryService.createTeamCategory(crewId, categoryId);
        if (teamCategory.getId() != null){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(JsonResultData.successResultBuilder()
                            .data(null)
                            .build());
        }else{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(JsonResultData.failResultBuilder()
                            .build());
        }
    }

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
