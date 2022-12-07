package com.co.kr.modyeo.api.bbs.domain.dto.search;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamArticleSearch {
    private String title;

    private Long teamId;

    private String content;

    private Long memberId;

    private Integer limit;

    private Integer offset;

    private String orderBy;

    private Sort.Direction direction;

    @Builder(builderMethodName = "of", builderClassName = "of")
    public TeamArticleSearch(Long teamId, String title, String content, Integer limit, Integer offset, String orderBy, Sort.Direction direction, Long memberId) {
        this.title = title;
        this.teamId = teamId;
        this.content = content;
        this.memberId = memberId;
        this.limit = limit != null ? limit : 20;
        this.offset = offset != null ? offset : 0;
        this.orderBy = orderBy != null ? orderBy : "id";
        this.direction = direction != null ? direction : Sort.Direction.DESC;
    }
}
