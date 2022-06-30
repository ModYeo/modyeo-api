package com.co.kr.modyeo.member.controller.api;

import com.co.kr.modyeo.common.result.JsonResultData;
import com.co.kr.modyeo.member.domain.entity.Category;
import com.co.kr.modyeo.member.domain.entity.link.CrewCategory;
import com.co.kr.modyeo.member.service.CrewCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CrewCategoryApiController {
    private final CrewCategoryService crewCategoryService;

    @PostMapping("/crew/{crew_id}/category/{category_id}")
    public ResponseEntity<?> createCrewCategory(
            @PathVariable("crew_id") Long crewId,
            @PathVariable("category_id") Long categoryId
    ){
        CrewCategory crewCategory = crewCategoryService.createCrewCategory(crewId, categoryId);
        if (crewCategory.getId() != null){
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

    @DeleteMapping("/crew_category/{crew_category_id}")
    public ResponseEntity<?> deleteCrewCategory(
            @PathVariable("crew_category_id") Long crewCategoryId
    ){
        crewCategoryService.deleteCrewCategory(crewCategoryId);
        return ResponseEntity.ok(JsonResultData
                .successResultBuilder()
                .data(null)
                .build());
    }

}
