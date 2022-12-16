package com.co.kr.modyeo.api.inquiry.domain.dto.search;

import com.co.kr.modyeo.api.inquiry.domain.enumerate.InquiryStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor
public class InquirySearch {
    private Long id;
    private InquiryStatus status;
    private Sort.Direction direction;
    private String orderBy;
    private String title;
    private String content;
    private Long createdBy;
    private Integer offset;
    private Integer limit;

    @Builder(builderMethodName = "of", builderClassName = "of")
    public InquirySearch(Long id, Sort.Direction direction, String orderBy, InquiryStatus status,
                         String title, String content, Long createdBy, Integer offset, Integer limit) {
        this.id = id;
        this.status = status;
        this.direction = direction != null ? direction : Sort.Direction.DESC;
        this.orderBy = orderBy != null ? orderBy : "id";
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.offset = offset != null ? offset : 0;
        this.limit = limit != null ? limit : 10;
    }
}
