package com.co.kr.modyeo.api.bbs.domain.dto.search;

import com.co.kr.modyeo.common.dto.SearchDto;
import lombok.*;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamArticleSearch extends SearchDto {
    private String title;

    private Long teamId;

    private String content;

    private Long memberId;

    @Builder(builderMethodName = "of", builderClassName = "of")
    public TeamArticleSearch(Long teamId, String title, String content, Long memberId) {
        this.title = title;
        this.teamId = teamId;
        this.content = content;
        this.memberId = memberId;
    }
}
