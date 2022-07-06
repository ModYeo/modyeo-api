package com.co.kr.modyeo.api.bbs.controller;

import com.co.kr.modyeo.api.bbs.domain.dto.request.ArticleRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleDetail;
import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.search.ArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.api.bbs.service.BoardService;
import com.co.kr.modyeo.common.result.JsonResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping("/article/{article_id}")
    public ResponseEntity<?> getArticle(
            @PathVariable(value = "article_id")Long id
    ){
        ArticleDetail articleDetail = boardService.getArticle(id);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(articleDetail)
                .build());
    }

    @GetMapping("/article")
    public ResponseEntity<?> getArticles(ArticleSearch articleSearch){
        Slice<ArticleResponse> articleResponses = boardService.getArticles(articleSearch);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(articleResponses)
                .build());
    }

    @PostMapping("/article")
    public ResponseEntity<?> createArticle(ArticleRequest articleRequest){
        boardService.createArticle(articleRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JsonResultData.successResultBuilder()
                        .data(null)
                        .build());
    }

    @PatchMapping("/article")
    public ResponseEntity<?> updateArticle(ArticleRequest articleRequest){
        boardService.updateArticle(articleRequest);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }

    @DeleteMapping("/article/{article_id}")
    public ResponseEntity<?> deleteArticle(
            @PathVariable(value = "article_id")Long articleId){
        boardService.deleteArticle(articleId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }
}
